package com.DeRivasRabary.insa.factory;

import com.DeRivasRabary.insa.network.packet.*;

public class PacketFactory {


    public static PacketFactory instance ;


    public static PacketFactory createInstance() {
        if (instance == null) {
            instance = new PacketFactory();
        }
        return instance;
    }


    public Message createPacketMessage(String ipSender, String ipReceiver, String pseudo, String message){
        Message packetMessage = new Message(ipSender, ipReceiver, pseudo, message);
        return packetMessage;
    }

    public Hello createPacketHello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        Hello packetHello = new Hello(ipSender,ipReceiver,pseudo,replyRec);
        return packetHello ;
    }

    public Bye createPacketBye(String ipSender, String ipReceiver, String pseudo){
        Bye packetBye = new Bye(ipSender, ipReceiver, pseudo);
        return packetBye ;
    }

}
