package com.DeRivasRabary.insa.network.packet;


import java.text.DateFormat;
import java.util.Date;

import static com.DeRivasRabary.insa.network.packet.TypePacket.BYE;
import static com.DeRivasRabary.insa.network.packet.TypePacket.HELLO;
import static com.DeRivasRabary.insa.network.packet.TypePacket.MESSAGE;


public abstract class PacketManager {

    public String ipSender ;
    public String ipReceiver ;
    public String pseudo ;
    public String date ;
    private TypePacket type ;

    /**
     * Constructeur de paquet commun à hello-bye-message
     * Ne sert que pour les différents paquets spécifiques
     * Génère la date du paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     */
    public PacketManager(String ipSender, String ipReceiver, String pseudo, TypePacket type){
        this.ipSender = ipSender ;
        this.ipReceiver = ipReceiver ;
        this.pseudo = pseudo ;
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
    public Boolean isPacketMESSAGE (){ return (type==MESSAGE); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketHELLO (){ return (type==HELLO); }
    /**
     * Méthode qui affirme si le packet est un HELLO
     * @return Boolean
     */
    public Boolean isPacketBYE (){ return (type==BYE); }


    /**
     * Méthode qui retourne l'IP source
     * @return String
     */



    public String getIpSender(){ return ipSender; }
    /**
     * Méthode qui retourne l'IP Dest
     * @return String
     */
    public String getIpReceiver() { return  ipReceiver; }

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




}
