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


    public String listenOnPort() throws Exception {
        DatagramPacket receiverPacket = new DatagramPacket(new byte[1000],1000);
        while(isRunning){
            receiverSocket.receive(receiverPacket);
            byte[] data = receiverPacket.getData();
            return String.valueOf(data);
        }
        return "";
    }


    public void close(){
        isRunning = false;
        this.receiverSocket.close();
    }
}
