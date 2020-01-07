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
        } finally {
            reader = null;
            writer = null;
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
                synchronized(this) {
                    this.clientID = res.getLogin();
                    clientTable.put(new Integer(clientID.getId()), client);
                }
            }
        }
        return res;
    }
    
    /**
     * 
     * @param req
     * @return
     */
    private ComData ticketList(ComData req) {
        return null;
    }
    
    
}
