package com.DeRivasRabary.insa.network;


import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPMessageReceiverManager{
    private static final int BUFFER_SIZE = 1000;
    /*private Boolean isRunning = true;
    private final DatagramSocket receiverSocket;
    public final int port;

    public UDPMessageReceiverManager (int port)throws Exception{
        this.port = port;
        this.receiverSocket = new DatagramSocket(this.port);
    }
*/

    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(port);
        DatagramPacket receiverPacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
        receiverSocket.receive(receiverPacket);
        byte[] data = receiverPacket.getData();
        incomingMessageListener.onNewIncominMessage(new String(data).trim());

    }

/*
    public void close(){
        isRunning = false;
        this.receiverSocket.close();
    }
    */
}
