package com.DeRivasRabary.insa.network.packet;


import java.text.DateFormat;
import java.util.Date;


public abstract class PacketManager {

    public String ipSender ;
    public String ipReceiver ;
    public String pseudo ;
    public String date ;

    /**
     * Constructeur de paquet commun à hello-bye-message
     * Ne sert que pour les différents paquets spécifiques
     * Génère la date du paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     */
    public PacketManager(String ipSender, String ipReceiver, String pseudo){
        this.ipSender = ipSender ;
        this.ipReceiver = ipReceiver ;
        this.pseudo = pseudo ;
        this.date = mediumDateFormat.format(new Date());
    }

    DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
}
