package com.DeRivasRabary.insa.network;


import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPMessageReceiverManager{
    private Boolean isRunning = true;
    private final DatagramSocket receiverSocket;
    public final int port;

    public UDPMessageReceiverManager (int port)throws Exception{
        this.port = port;
        this.receiverSocket = new DatagramSocket(this.port);
    }

    public DatagramPacket listenOnPort() throws Exception {
        DatagramPacket receiverPacket = new DatagramPacket(new byte[1000],1000);
        while(isRunning){
            receiverSocket.receive(receiverPacket);
        }
        return receiverPacket;
    }


    public void close(){
        isRunning = false;
        this.receiverSocket.close();
    }
}
