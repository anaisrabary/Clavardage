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

    /**
     * Méthode qui doit etre utilisée pour créer un paquet message
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param message
     * @return
     */
    public Message createPacketMessage(String ipSender, String ipReceiver, String pseudo, String message){
        Message packetMessage = new Message(ipSender, ipReceiver, pseudo, message);
        return packetMessage;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un hello
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param replyRec
     * @return
     */
    public Hello createPacketHello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        Hello packetHello = new Hello(ipSender,ipReceiver,pseudo,replyRec);
        return packetHello ;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un bye
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @return
     */
    public Bye createPacketBye(String ipSender, String ipReceiver, String pseudo){
        Bye packetBye = new Bye(ipSender, ipReceiver, pseudo);
        return packetBye ;
    }

}
