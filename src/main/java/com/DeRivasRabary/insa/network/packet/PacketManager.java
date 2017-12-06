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
    public TypePacket type ;

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


}
