package com.DeRivasRabary.insa.model.packet;


import java.net.InetAddress;

public final class   Notification extends PacketManager {

    public enum Notification_type {
        CONNECT,
        DISCONNECT,
        STATUS_CHANGE,
        ACK_CONNECT
    }

    private Notification_type typeN;
    private String data;

    /**
     * Constructeur d'un Hello pour dire aux autres qu'on est sur le réseau
     * Utiliser la classe PacketFactory pour construire un paquet
     * Si replyRec est vrai on demande à tous les récepteurs de nous envoyer un hello en retour
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoDestinataire
     * @param typeN
     * @param data
     */
    public Notification(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoDestinataire, Notification_type typeN, String data) {
        super(ipSender, ipReceiver, pseudoEmetteur, pseudoDestinataire, TypePacket.NOTIF);
        this.typeN = typeN;
        this.data = data;
    }


    public Notification_type getTypeN() {
        return this.typeN;
    }

    public String getData() {
        return this.data;
    }


    public static boolean isNotifString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isMsg = msgstr.regionMatches(position,"Type : Notif\n",0,13);
        return isMsg;
    }

    // TODO : getPseudoEmmeteur() au lieu de getpseudo(). Tu valides que c'est pseudo emmeteur et pas recepeteur ?
    public String toString() {
        switch (this.typeN) {
            case CONNECT :
                return "NOTIFICATION PACKET : "+this.getPseudoEmmeteur() + " s'est connecté.";
            case DISCONNECT :
                return this.getPseudoEmmeteur() + " s'est déconnecté.";
            case STATUS_CHANGE :
                switch (this.data) {
                    case "ONLINE" :
                        return this.getPseudoEmmeteur() + " est passé \"en ligne\".";
                    case "BUSY" :
                        return this.getPseudoEmmeteur() + " est passé \"occupé\".";
                }
                break;
        }
        return "Notification inconnue";
    }

}
