package com.DeRivasRabary.insa.model.packet;


import java.net.InetAddress;

public final class   Notification extends PacketManager {

    public enum Notification_type {
        CONNECT,
        DISCONNECT,
        STATUS_CHANGE,
        ACK_CONNECT
    }

    private Notification_type type;
    private String data;

    /**
     * Constructeur d'un Hello pour dire aux autres qu'on est sur le réseau
     * Utiliser la classe PacketFactory pour construire un paquet
     * Si replyRec est vrai on demande à tous les récepteurs de nous envoyer un hello en retour
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param type
     * @param data
     */
    public Notification(InetAddress ipSender, InetAddress ipReceiver, String pseudo, Notification_type type, String data) {
        super(ipSender, ipReceiver, pseudo, TypePacket.NOTIF);
        this.type = type;
        this.data = data;
    }


    public Notification_type getType() {
        return this.type;
    }

    public String getData() {
        return this.data;
    }


    public static boolean isNotifString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isMsg = msgstr.regionMatches(position,"Type : Notif\n",0,13);
        return isMsg;
    }


    public String toString() {
        switch (this.type) {
            case CONNECT :
                return this.getPseudo() + " s'est connecté.";
            case DISCONNECT :
                return this.getPseudo() + " s'est déconnecté.";
            case STATUS_CHANGE :
                switch (this.data) {
                    case "ONLINE" :
                        return this.getPseudo() + " est passé \"en ligne\".";
                    case "BUSY" :
                        return this.getPseudo() + " est passé \"occupé\".";
                }
                break;
        }
        return "Notification inconnue";
    }

}
