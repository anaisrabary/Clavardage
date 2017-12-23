package com.DeRivasRabary.insa.model.packet;


import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

public abstract class PacketManager implements Serializable{

    private InetAddress ipSender ;
    private InetAddress ipReceiver ;
    private String pseudoEmmeteur ;
    private String pseudoDestinataire ;
    private String date ;
    private TypePacket type ;

    /**
     * Constructeur de paquet commun à hello-bye-message
     * Ne sert que pour les différents paquets spécifiques
     * Génère la date du paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoDestinataire
     * @param type
     */
    public PacketManager(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoDestinataire, TypePacket type){
        this.ipSender = ipSender ;
        this.ipReceiver = ipReceiver ;
        this.pseudoEmmeteur = pseudoEmetteur ;
        this.pseudoDestinataire = pseudoDestinataire ;
        this.date = mediumDateFormat().format(new Date());
        this.type = type;
    }

    private DateFormat mediumDateFormat() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    }

    // TODO : les méthodes isPacketMachin ne servent pas car on reçoit un string de l'autre coté. Dans ces méthodes il faut récupérer le type dans la première ligne du paquet.

    /**
     * Méthode qui affirme si le packet est un MESSAGE
     * @return Boolean
     */
    public Boolean isPacketMESSAGE (){ return (type==TypePacket.MESSAGE); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketHELLO (){ return (type==TypePacket.HELLO); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketBYE (){ return (type==TypePacket.BYE); }


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

    public String getPseudoEmmeteur() { return pseudoEmmeteur;}

    public String getPseudoDestinataire() { return pseudoDestinataire;}

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
        String zetype ;
        if (this.type==TypePacket.BYE){
             zetype = "Type : Bye\n";
        }
        else if (this.type==TypePacket.HELLO){
             zetype = "Type : Hello\n";
        }
        else if (this.type==TypePacket.MESSAGE){
             zetype = "Type : Message\n";
        }
        else {
             zetype = "Type : Unknown\n";
        }
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudoe = "PseudoEmetteur : " + this.pseudoEmmeteur + "\n" ;
        String pseudod = "PseudoDestinataire : " + this.pseudoDestinataire +"\n" ;

        return zetype + ipsender + ipreceiver + date + pseudoe + pseudod ;
    }



}
