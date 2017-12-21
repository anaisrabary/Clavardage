package com.DeRivasRabary.insa.model.packet.factory;

import com.DeRivasRabary.insa.model.packet.*;

import java.net.InetAddress;

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
    public Message createPacketMessage(InetAddress ipSender, InetAddress ipReceiver, String pseudo, String message){
        Message packetMessage = new Message(ipSender, ipReceiver, pseudo, message);
        return packetMessage;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un hello
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param type
     * @param data
     * @return
     */
    public Hello createPacketHello(InetAddress ipSender, InetAddress ipReceiver, String pseudo, Hello.Control_type type, int data){
        Hello packetHello = new Hello(ipSender,ipReceiver,pseudo, type, data);
        return packetHello ;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un bye
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @return
     */
    public Bye createPacketBye(InetAddress ipSender, InetAddress ipReceiver, String pseudo){
        Bye packetBye = new Bye(ipSender, ipReceiver, pseudo);
        return packetBye ;
    }

}
