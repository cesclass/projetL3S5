package interfaces;

import java.io.*;
import java.net.*;
import java.util.*;

import communications.*;
import database.*;
import exchange.*;

public class ClientInterface implements Runnable {

    private static Map<Integer, Socket> clientTable = new HashMap<>();
    private static DBManager dbm = new DBManager();

    private Socket client = null;
    private ComLogin clientID = null;    
    private boolean working = true;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private String recvStr = null;
    private String emitStr = null;
    private ComData recvObj = null;
    private ComData emitObj = null;

    /**
     * 
     * @param client
     */
    public ClientInterface(Socket client) {
        this.client = client;
        try {
            this.reader = new BufferedReader( new InputStreamReader(
                    client.getInputStream())
            );
            this.writer = new BufferedWriter( new OutputStreamWriter(
                    client.getOutputStream())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            while(working) {
                recvStr = reader.readLine();
                if(recvStr != null) {
                    recvObj = Serializer.deserialize(recvStr);
        
                    emitObj = handling(recvObj);
        
                    emitStr = Serializer.serialize(emitObj);
                    writer.write(emitStr);
                    writer.newLine();
                    writer.flush();
                } else {
                    logout();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData handling(ComData req) {
        switch (req.getType()) {
            case CONNECT_RQ:
                return login(req);
            
            case DISCONNECT_CLI:
                return logout();

            case GROUPS_RQ:
                return dbm.groupList();

            case TICKET_LIST_RQ: 
                return ticketsList(req);
            
            case TICKET_RQ:
                return ticket(req);

            case NEW_TICKET_CLI:
                return newTicket(req);

            case NEW_MESSAGE_CLI:
                return newMessage(req);
                
            case STATUSES_RQ:
                return dbm.statuses(req);

            default:
                return new ComData(ComType.ERROR_INVALID_REQUEST);
        }
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData login(ComData req) {
        ComData res = dbm.login(req);
        if(res.getType() == ComType.CONNECT_RP) {
            if(clientTable.containsKey(res.getLogin().getId())) {
                return new ComData(ComType.ERROR_ALREADY_CONNECTED);
            } else {
                this.clientID = res.getLogin();
                synchronized(this) {
                    clientTable.put(new Integer(clientID.getId()), client);
                }
            }
        }
        return res;
    }

    /**
     * 
     * @return
     */
    private ComData logout() {
        if(clientID != null) {
            synchronized(this) {
                clientTable.remove(new Integer(clientID.getId()));
            }
        }
        working = false;
        return new ComData(ComType.DISCONNECT_CLI);
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData ticketsList(ComData req) {
        ComData res = dbm.ticketsList(req);
        new Thread(new Updater(req, res)).start();
        return res;
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData ticket(ComData req) {
        ComData res = dbm.ticket(req);
        new Thread(new Updater(req, res)).start();
        return res;
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData newTicket(ComData req) {
        // ComData res = dbm.newTicket(req);

        return null;
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData newMessage(ComData req) {
        ComData res = dbm.newMessage(req);
        new Thread(new Updater(req, res)).start();
        return res;
    }

    private class Updater implements Runnable {

        private ComData req = null;
        private ComData res = null;

        public Updater(ComData req, ComData res) {
            this.req = req;
            this.res = res;
        }

        @Override
        public void run() {
            switch (req.getType()) {
                case TICKET_LIST_RQ:
                    updateOnLogin();
                    break;
                
                case TICKET_RQ:
                    updateOnLoad();
                    break;
                
                case NEW_MESSAGE_CLI:
                    spreadMessage();
                    break;

                default:
                    break;
            }
        }

        private void sending(Socket sock, ComData data) {
            BufferedWriter wsock = null;
            try {
                wsock = new BufferedWriter( new OutputStreamWriter(
                        sock.getOutputStream()));
                wsock.write(Serializer.serialize(data));
                wsock.newLine();
                wsock.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void updateOnLogin() {
            for(Ticket ticket : res.getTickets()) {
                List<Integer> users = dbm.ticketUsersID(ticket);
                if(!users.contains(clientID.getId())) {
                    users.add(clientID.getId());
                }
                
                ComData update = dbm.updateMsgStatus(
                        ticket, clientID.getId(), 
                        StatusType.WAITING, StatusType.RECEIVED
                );

                if(update.getMessages().size() > 0) {
                    for(Integer uid : users) {
                        if(clientTable.containsKey(uid)) {
                            sending(clientTable.get(uid), update);
                        }
                    }
                }
            }
        }

        private void updateOnLoad() {
            Ticket ticket = res.getTickets().get(0);
            List<Integer> users = dbm.ticketUsersID(ticket);
            if(!users.contains(clientID.getId())) {
                users.add(clientID.getId());
            }

            ComData update = dbm.updateMsgStatus(
                    ticket, clientID.getId(), 
                    StatusType.RECEIVED, StatusType.READ
            );

            if(update.getMessages().size() > 0) {
                for(Integer uid : users) {
                    if(clientTable.containsKey(uid)) {
                        sending(clientTable.get(uid), update);
                    }
                }
            }
        }

        private void spreadMessage() {
            Ticket ticket = res.getTickets().get(0);
            Message msg = res.getMessages().get(0);
            List<Integer> users = dbm.ticketUsersID(ticket);
            users.remove(new Integer(clientID.getId()));

            ComData newmsg = new ComData(ComType.NEW_MESSAGE_SRV);
            newmsg.getTickets().add(ticket);
            newmsg.getMessages().add(msg);

            for(Integer uid : users) {
                if(clientTable.containsKey(uid)) {
                    sending(clientTable.get(uid), newmsg);
                    dbm.userRecvMsg(uid.intValue(), msg.getId());
                }
            }

            ComData update = dbm.updateMsgStatus(
                    ticket, clientID.getId(), 
                    StatusType.WAITING, StatusType.RECEIVED
            );

            if(update.getMessages().size() > 0) {
                for(Integer uid : users) {
                    if(clientTable.containsKey(uid)) {
                        sending(clientTable.get(uid), update);
                    }
                }
            }

        }

    }
}
