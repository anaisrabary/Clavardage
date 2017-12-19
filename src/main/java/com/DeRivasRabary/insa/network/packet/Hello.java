package com.DeRivasRabary.insa.network.packet;

import static com.DeRivasRabary.insa.network.packet.TypePacket.HELLO;

public class Hello extends PacketManager {

    public Boolean replyRec;

    /**
     * Constructeur d'un Hello pour dire aux autres qu'on est sur le réseau
     * Utiliser la classe PacketFactory pour construire un paquet
     * Si replyRec est vrai on demande à tous les récepteurs de nous envoyer un hello en retour
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param replyRec
     */
    public Hello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec) {
        super(ipSender, ipReceiver, pseudo, HELLO);
        this.replyRec = replyRec;
    }

    @Override
    public String toString() {
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Hello\n";
        String ipsender = "IPSource : " + this.ipSender + "\n";
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n";
        String replyrec = "ReplyRec : " + Boolean.toString(this.replyRec) + "\n";
        return delimiter + type + ipsender + ipreceiver + date + pseudo + replyrec + delimiter;
    }

    public String toDisplaySend() {
        String type = "Type : Hello\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n";
        String replyrec = "ReplyRec : " + Boolean.toString(this.replyRec) + "\n";
        return type + date + pseudo + replyrec;
    }


    public static boolean isHelloString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isMsg = msgstr.regionMatches(position,"Type : Hello\n",0,13);
        return isMsg;
    }


    public static Hello stringToHello(String msgstr,String ipsender, String ipreceiver) {
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

        return new Hello(ipsender,ipreceiver,pseudo,replyrec);
    }
}
