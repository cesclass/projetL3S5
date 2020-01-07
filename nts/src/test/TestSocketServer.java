package test;

import java.io.*;
import java.net.*;
import java.util.*;

import interfaces.ClientInterface;

public class TestSocketServer {

    public static void main(String[] args) throws IOException {

        Map<Integer, Socket> client_table = new HashMap<>();

        Thread tserv = new Thread(new Runnable() {
            private int port = 9999;
            private ServerSocket server = null;
            private Socket client = null;

            @Override
            public void run() {
                try {
                    server = new ServerSocket(port);
                    System.out.println("Start serv on port " + port);
                    for (int i = 0; i < 5; i++) {
                        client = server.accept();
                        client_table.put(i, client);
                        Thread c = new Thread(new ClientInterface(client));
                        c.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (server != null) {
                            server.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tserv.start();

        boolean test = true;
        while (test) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!client_table.isEmpty()) {
                Socket s = client_table.get(client_table.keySet().iterator().next());
                System.out.println( s.getPort() + " Socket access out of thread");
                test = false;
            }
        }
    }
}
