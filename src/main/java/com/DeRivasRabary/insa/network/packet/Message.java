package com.DeRivasRabary.insa.network.packet;


import java.util.Scanner;

import static com.DeRivasRabary.insa.network.packet.TypePacket.MESSAGE;

/**
 * Classe message hérite de packetManager et construit un message pour une conversation
 */
public class Message extends PacketManager{

    public String message ;

    /**
     * Constructeur de message classique pour une conversation
     * Utiliser la classe PacketFactory pour construire un paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param message
     */
    public Message(String ipSender, String ipReceiver, String pseudo, String message){
        super(ipSender, ipReceiver, pseudo, MESSAGE);
        this.message = message ;
    }

    @Override
    public String toString(){
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Message\n";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String message = "Message : " + this.message +"\n" ;
        return delimiter + type + ipsender + ipreceiver + date + pseudo + message + delimiter ;
    }

    public String toDisplaySend(){
        String type = "Type : Message\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String message = "Message : " + this.message +"\n" ;
        return type + date + pseudo + message;
    }

    public static Message stringToMessage(String msgstr){
        TypePacket typePacket = MESSAGE ;
        Scanner sc = new Scanner(System.in);
        String typeString = sc.findInLine("\n");



        return new Message("","","","");
    }
}
