package test;

import java.io.*;
import java.net.*;

import exchange.*;

public class TestComCliServ {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55842;
        
        ComLogin login = new ComLogin("scc1255a", "foobar");
        ComData data = new ComData(ComType.CONNECT_RQ, login);
        ComData logout = new ComData(ComType.DISCONNECT_CLI);

        try {
            Socket sock = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            String s = Serializer.serialize(data);
            System.out.println("client send: " + s);

            writer.write(s);
            writer.newLine();
            writer.flush();
            
            s = reader.readLine();
            System.out.println("client recv: " + s);

            writer.write(Serializer.serialize(logout));
            writer.newLine();
            writer.flush();

            sock.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
