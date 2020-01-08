package applications;

import java.io.*;
import java.net.*;

import interfaces.ClientInterface;

public class Serveur {

    public static void main(String[] args) throws IOException {

        new Thread(new Runnable() {
            private int port = 55842;
            private ServerSocket server = null;
            private Socket client = null;

            @Override
            public void run() {
                try {
                    server = new ServerSocket(port);
                    System.out.println("Start serv on port " + port);
                    while (true) {
                        client = server.accept();
                        new Thread(new ClientInterface(client)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // suite du main
    }
}
