package com.DeRivasRabary.insa.network;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMessageReceiverManager implements MessageReceiverService {
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        Thread accept = new Thread(new AcceptClients(serverSocket,incomingMessageListener));
        accept.start();
        //throw new UnsupportedOperationException();
    }
}
