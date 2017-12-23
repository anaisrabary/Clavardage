package com.DeRivasRabary.insa.model.packet;


import java.net.InetAddress;

/**
 * Classe message hérite de packetManager et construit un message pour une conversation
 */
public final class Message extends PacketManager{

    private String message ;

    /**
     * Constructeur de message classique pour une conversation
     * Utiliser la classe PacketFactory pour construire un paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoDestinataire
     * @param message
     */
    public Message(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoDestinataire, String message){
        super(ipSender, ipReceiver, pseudoEmetteur, pseudoDestinataire, TypePacket.MESSAGE);
        this.message = message;
    }


    /*
    public static boolean isMessageString(String msgstr){
        int position = msgstr.indexOf("Type :");
        boolean isMsg = msgstr.regionMatches(position,"Type : Message\n",0,15);
        return isMsg;
    }


    public static Message stringToMessage(String msgstr,InetAddress ipSender, InetAddress ipReceiver) {
        int positionMSG = 10 + msgstr.indexOf("Message : ");
        int positionPseudo = 9 + msgstr.indexOf("Pseudo : ");
        int finpseudo = msgstr.indexOf("\n",positionPseudo);


        // recuperation du message
        int length = msgstr.length();
        char[] dst = new char[length - positionMSG];
        msgstr.getChars(positionMSG, length, dst, 0);
        String message = new String(dst);
        // fin récupération message

        // recuperation pseudo
        int pseudolength = finpseudo - positionPseudo;
        char[] charpseudo = new char[pseudolength];
        msgstr.getChars(positionPseudo,finpseudo,charpseudo,0);
        String pseudo = new String(charpseudo);
        //fin recuperation pseudo

        return new Message(ipSender, ipReceiver, pseudo, message);
    }
    */

    public String getData() {
        return this.message;
    }

    public String toString(){
        String zetype = "Type : Message\n";
        String ipsender = "IPSource : " + this.getIpSender() + "\n" ;
        String ipreceiver = "IPDestination : " + this.getIpReceiver() + "\n";
        String date = "Date : " + this.getDate() + "\n";
        String pseudoe = "PseudoEmetteur : " + this.getPseudoEmmeteur() + "\n" ;
        String pseudod = "PseudoDestinataire : " + this.getPseudoDestinataire() +"\n" ;
        String message = "Message : " + this.getData() +"\n";

        return zetype + ipsender + ipreceiver + date + pseudoe + pseudod + message ;
    }


}
