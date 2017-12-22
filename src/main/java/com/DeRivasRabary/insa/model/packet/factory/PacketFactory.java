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
     * @param pseudoEmmeteur
     * @param pseudoRecepteur
     * @param message
     * @return
     */

    public Message createPacketMessage(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmmeteur,
                                       String pseudoRecepteur,String message){
        Message packetMessage = new Message(ipSender, ipReceiver, pseudoEmmeteur, pseudoRecepteur, message);
        return packetMessage;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un hello
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoRecepteur
     * @param type
     * @param data
     * @return
     */

    public Hello createPacketHello(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoRecepteur,
                                   Hello.Control_type type, int data){
        Hello packetHello = new Hello(ipSender,ipReceiver,pseudoEmetteur,pseudoRecepteur, type, data);
        return packetHello ;
    }

    /**
     * Méthode qui doit etre utilisée pour créer un bye
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoRecepteur
     * @return
     */

    public Bye createPacketBye(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoRecepteur){
        Bye packetBye = new Bye(ipSender, ipReceiver, pseudoEmetteur,pseudoRecepteur);
        return packetBye ;
    }

}
