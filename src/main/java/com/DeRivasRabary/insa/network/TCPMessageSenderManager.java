/*package com.DeRivasRabary.insa.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageSenderManager implements MessageSenderService {
    @Override
    public void sendMessageOn(String ipAddress, int port, String message) throws Exception {
        Socket chatSocket = new Socket();
        chatSocket.connect(new InetSocketAddress(ipAddress,port)); //ask for connection
        InputStreamReader stream = new InputStreamReader(chatSocket.getInputStream());
        PrintWriter writer = new PrintWriter(chatSocket.getOutputStream());
        writer.println(message);
        writer.flush();
        chatSocket.close();
        //throw new UnsupportedOperationException();
    }
}
*/