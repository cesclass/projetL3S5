package test;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TestSocketClient {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 9999;
        List<Socket> socks = new ArrayList<>();
        
        try {
            for(int i = 0; i < 5; i++) {
                socks.add(new Socket(host, port));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for(Socket s : socks) {
                s.close();
            }
        }
	}

}
