package interfaces;

import java.io.*;
import java.net.*;
// import java.util.*;

public class ClientInterface implements Runnable {

    // static Map<Integer, Socket> client_table = new HashMap<>();

    private Socket client = null;
    // private PrintWriter writer = null;
    // private BufferedInputStream reader = null;

    public ClientInterface(Socket client) {
        // client_table.put(client.getPort(), client);
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("client port: " + client.getPort());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        // } finally {
        //     client_table.remove(client.getPort());
        }
    }
    
}
