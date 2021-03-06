package com.DeRivasRabary.insa.model.packet;



import java.net.InetAddress;

public class Hello extends PacketManager {

    public enum Control_type {
        HELLO,
        ACK,
        TMP_SOCKET,
        TMP_SOCKET_ACK
    }

    private Control_type type;
    private int data;

    /**
     * Constructeur d'un Hello pour dire aux autres qu'on est sur le réseau
     * Utiliser la classe PacketFactory pour construire un paquet
     * Si replyRec est vrai on demande à tous les récepteurs de nous envoyer un hello en retour
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoDestinataire
     * @param type
     * @param data
     */
    public Hello(InetAddress ipSender,InetAddress ipReceiver, String pseudoEmetteur, String pseudoDestinataire, Control_type type, int data) {
        super(ipSender, ipReceiver, pseudoEmetteur, pseudoDestinataire, TypePacket.HELLO);
        this.type = type;
        this.data = data;
    }

    public static boolean isHelloString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isMsg = msgstr.regionMatches(position,"Type : Hello\n",0,13);
        return isMsg;
    }

    public int getData() {
        return data;
    }

    public Control_type getType() {
        return type;
    }


    /*
    public static Hello stringToHello(String msgstr,InetAddress ipsender, InetAddress ipreceiver) {
        boolean replyrec;
        // pour le replyrec
        int position = 11 + msgstr.indexOf("ReplyRec : ");
        int length = msgstr.length();
        char[] dst = new char[length - position];
        msgstr.getChars(position, length, dst, 0);
        String message = new String(dst);
        if (message.equals("true\n")) replyrec=true;
        else replyrec=false;

        // pour le pseudo
        int positionPseudo = 9 + msgstr.indexOf("Pseudo : ");
        int finpseudo = msgstr.indexOf("\n",positionPseudo);
        // recuperation pseudo
        int pseudolength = finpseudo - positionPseudo;
        char[] charpseudo = new char[pseudolength];
        msgstr.getChars(positionPseudo,finpseudo,charpseudo,0);
        String pseudo = new String(charpseudo);
        //fin recuperation pseudo

        return new Hello(ipsender,ipreceiver,pseudo,type, data);
    }
    */

    public String toString(){
        String zetype = "Type : Hello\n";
        String ipsender = "IPSource : " + this.getIpSender() + "\n" ;
        String ipreceiver = "IPDestination : " + this.getIpReceiver() + "\n";
        String date = "Date : " + this.getDate() + "\n";
        String pseudoe = "PseudoEmetteur : " + this.getPseudoEmmeteur() + "\n" ;
        String pseudod = "PseudoDestinataire : " + this.getPseudoDestinataire() +"\n";
        String controlType = "ControlType : "+ this.getType().toString() +"\n";
        String data = "Data : " + this.getData() + "\n";

        return zetype + ipsender + ipreceiver + date + pseudoe + pseudod + controlType + data ;
    }

}
