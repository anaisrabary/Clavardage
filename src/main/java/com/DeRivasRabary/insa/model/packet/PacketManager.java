package com.DeRivasRabary.insa.model.packet;


import javafx.beans.property.SimpleObjectProperty;

import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

public abstract class PacketManager {

    private InetAddress ipSender ;
    private InetAddress ipReceiver ;
    private String pseudo ;
    private String date ;
    private SimpleObjectProperty type ;

    /**
     * Constructeur de paquet commun à hello-bye-message
     * Ne sert que pour les différents paquets spécifiques
     * Génère la date du paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     */
    public PacketManager(InetAddress ipSender, InetAddress ipReceiver, String pseudo, TypePacket type){
        this.ipSender = ipSender ;
        this.ipReceiver = ipReceiver ;
        this.pseudo = pseudo ;
        this.date = mediumDateFormat().format(new Date());
        this.type = new SimpleObjectProperty(type);
    }

    private DateFormat mediumDateFormat() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    }

    // TODO : les méthodes isPacketMachin ne servent pas car on reçoit un string de l'autre coté. Dans ces méthodes il faut récupérer le type dans la première ligne du paquet.

    /**
     * Méthode qui affirme si le packet est un MESSAGE
     * @return Boolean
     */
    public Boolean isPacketMESSAGE (){ return (type.get()==TypePacket.MESSAGE); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketHELLO (){ return (type.get()==TypePacket.HELLO); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketBYE (){ return (type.get()==TypePacket.BYE); }


    /**
     * Méthode qui retourne l'IP source
     * @return String
     */



    public InetAddress getIpSender(){ return ipSender; }
    /**
     * Méthode qui retourne l'IP Dest
     * @return String
     */
    public InetAddress getIpReceiver() { return  ipReceiver; }

    public String getPseudo() { return pseudo;}

    public String getDate() { return date;  }

    public static Boolean isPacketMessageReceived (String message){
        if (message.contains("Type : Message")){
            return true ;
        }
        return false ;
    }

    public static Boolean isPacketHelloReceived (String message){
        if (message.contains("Type : Hello")){
            return true ;
        }
        return false ;
    }

    public static Boolean isPacketByeReceived (String message){
        if (message.contains("Type : Bye")){
            return true ;
        }
        return false ;
    }


    @Override
    public String toString(){
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Message\n";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        return delimiter + type + ipsender + ipreceiver + date + pseudo +delimiter ;
    }



}
