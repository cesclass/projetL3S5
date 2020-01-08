package interfaces;

import java.io.*;
import java.net.*;
import java.util.*;

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
                recvObj = Serializer.deserialize(recvStr);
    
                emitObj = handling(recvObj);
    
                emitStr = Serializer.serialize(emitObj);
                writer.write(emitStr);
                writer.newLine();
                writer.flush();
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
                return groupList();

            case TICKETS_LIST_RQ:
                return ticketList(req);

                
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
        if(res.getType() == ComType.CONNECT_OK) {
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
        synchronized(this) {
            clientTable.remove(new Integer(clientID.getId()));
        }
        working = false;
        return new ComData(ComType.DISCONNECT_SRV);
    }
    
    /**
     * 
     * @return
     */
    private ComData groupList() {
        // TODO groupList dbm.(SELECT * FROM groups)
        return null;
    }

    /**
     * 
     * @param req
     * @return
     */
    private ComData ticketList(ComData req) {
        // TODO ticketList dbm.(SELECT * FROM ticket WHERE "user")
        return null;
    }


    

}
