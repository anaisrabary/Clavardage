package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;

public class ClavardageNI {
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

    public String onReceive()throws Exception{
        try {
            System.out.println("here");
            UDPMessageReceiverManager udpMessageReceiverManager = new UDPMessageReceiverManager(PORT);
            String data = udpMessageReceiverManager.listenOnPort() ;
            return data;
        }
        catch (Exception e){
            System.out.println("yolo");
            e.printStackTrace();
            return "";
        }
    }



}
