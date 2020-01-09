package interfaces;

import java.io.*;
import java.net.Socket;

import communications.Message;
import communications.Ticket;
import exchange.*;
import gui.ClientApplication;
import gui.Login;

public class ServerInterface implements Runnable {
    // *****************************************************************
    // *
    // * CONSTANTS
    // *
    // *****************************************************************

    /** Host address for Socket */
    private final static String HOST = "127.0.0.1";
    /** Port for Socket */
    private final static int PORT = 55842;

    // *****************************************************************
    // *
    // * ATTRIBUTES
    // *
    // *****************************************************************

    private Socket sock;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean working;
    private UserInterface ui;
    private Object app;

    // *****************************************************************
    // *
    // * CONSTRUCTOR
    // *
    // *****************************************************************

    public ServerInterface(UserInterface ui, Login app) {
        this.ui = ui;
        this.app = app;

        try {
            sock = new Socket(HOST, PORT);
            reader = new BufferedReader( new InputStreamReader(
                    sock.getInputStream()
            ));
            writer = new BufferedWriter( new OutputStreamWriter(
                    sock.getOutputStream()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        working = true;
    }

    @Override
    public void run() {
        String recv;
        try {
            while (working) {
                recv = reader.readLine();
                System.err.println(recv);
                handling(Serializer.deserialize(recv));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handling(ComData data) {
        switch (data.getType()) {
            // ERROR CASES
            case ERROR_BAD_LOGIN:
                ((Login) app).errorBadLogin();
                break;
            
            case ERROR_ALREADY_CONNECTED:
                ((Login) app).errorAlreadyConnected();
                break;

            // RESPONSE CASES
            case CONNECT_RP:
                ui.recvConnect(data);
                app = ((Login) app).successConnect();
                break;

            case TICKET_LIST_RP:
                for (Ticket ticket : data.getTickets()) {
                    ui.addTicket(ticket);
                }
                ((ClientApplication) app).updateTreeUI();
                break;

            case TICKET_RP:
                for (Message message : data.getMessages()) {
                    data.getTickets().get(0).addMessage(message);
                }
                ((ClientApplication) app).recvMessages(
                        data.getTickets().get(0));
                break;

            case STATUSES_RP:
                ((ClientApplication) app).recvStatuses(
                        data.getMessages().get(0),
                        data.getStatuses());
                break;
            
            case NEW_MESSAGE_RP:
            case NEW_MESSAGE_SRV:
                ui.recvMessages(
                        data.getTickets().get(0), 
                        data.getMessages().get(0));
                ((ClientApplication) app).recvMessages(
                        data.getTickets().get(0));
                break;

            // SERVER CASES
            case UPDATE_STATUS_SRV:
                ui.recvStatuses(data.getTickets().get(0),
                        data.getMessages());
                ((ClientApplication) app).updateTableUI();
                break;

            case DISCONNECT_SRV:
                working = false;
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        
            default:
                // err, bad type
                break;
        }
    }

    // *****************************************************************
    // *
    // * METHODS
    // *
    // *****************************************************************

    public void tryConnect(ComLogin cl) {
        ComData conRQ = new ComData(ComType.CONNECT_RQ, cl);
        String conRQ_s = Serializer.serialize(conRQ);

        send(conRQ_s);  
    }

    public void disconnect() {
        ComData disRQ = new ComData(ComType.DISCONNECT_CLI);
        String disRQ_s = Serializer.serialize(disRQ);

        send(disRQ_s);
    }
    
    
	public void pullTickets(ComLogin cl) {
        ComData tikRQ = new ComData(ComType.TICKET_LIST_RQ, cl);
        String tikRQ_s = Serializer.serialize(tikRQ);

        send(tikRQ_s);
    }
    
    public void pullMessages(ComLogin cl, Ticket ticket) {
        ComData mesRQ = new ComData(ComType.TICKET_RQ, cl);
        mesRQ.getTickets().add(ticket);
        String mesRQ_s = Serializer.serialize(mesRQ);

        send(mesRQ_s);
    }
    
    public void pullStatus(ComLogin cl, Message message) {
        ComData staRQ = new ComData(ComType.STATUSES_RQ, cl);
        staRQ.getMessages().add(message);
        String staRQ_s = Serializer.serialize(staRQ);

        send(staRQ_s);
    }
    
    public void pushMessage(ComLogin cl, Ticket ticket, Message message) {
        ComData sndRQ = new ComData(ComType.NEW_MESSAGE_CLI, cl);
        sndRQ.getTickets().add(ticket);
        sndRQ.getMessages().add(message);
        String sndRQ_s = Serializer.serialize(sndRQ);

        send(sndRQ_s);


	}

    private void send(String data) {
        try {
            writer.write(data);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
}
