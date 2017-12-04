package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;

public class ClavardageNI implements IncomingMessageListener{
    private  static final int PORT = 1234;
    public ClavardageNI(){

    }


    public void onSend(String ipAddress, String port, String message ) {
       UDPMessageSenderManager udpMessageSenderManager = new UDPMessageSenderManager();
       try {
           udpMessageSenderManager.sendMessageOn(ipAddress, port, message);
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    public void onReceive()throws Exception{
        try {
            System.out.println("here");
            UDPMessageReceiverManager udpMessageReceiverManager = new UDPMessageReceiverManager();
            udpMessageReceiverManager.listenOnPort(PORT,this ) ;

        }
        catch (Exception e){
            System.out.println("yolo");
            e.printStackTrace();
        }
    }


    @Override
    public void onNewIncominMessage(String message) {
       System.out.println("New incoming message: " + message);
    }

}
