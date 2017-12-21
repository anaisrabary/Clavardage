package com.DeRivasRabary.insa.model.packet;


import java.net.InetAddress;

/**
 * Classe message hérite de packetManager et construit un message pour une conversation
 */
public class Message extends PacketManager{

    private String message ;

    /**
     * Constructeur de message classique pour une conversation
     * Utiliser la classe PacketFactory pour construire un paquet
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param message
     */
    public Message(InetAddress ipSender, InetAddress ipReceiver, String pseudo, String message){
        super(ipSender, ipReceiver, pseudo, TypePacket.MESSAGE);
        this.message = message;
    }



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


    public String getData() {
        return this.message;
    }
}
