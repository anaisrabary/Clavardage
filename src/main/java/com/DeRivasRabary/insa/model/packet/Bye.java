package com.DeRivasRabary.insa.model.packet;

import java.net.InetAddress;

public class Bye extends PacketManager {


    /**
     * Constructeur du Bye pour signaler à tout le monde qu'on s'en va
     * Utiliser la classe PacketFactory pour construire un paquet
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudoEmetteur
     * @param pseudoDestinataire
     */
    public Bye(InetAddress ipSender, InetAddress ipReceiver, String pseudoEmetteur, String pseudoDestinataire) {
        super(ipSender, ipReceiver, pseudoEmetteur, pseudoDestinataire, TypePacket.BYE);
    }


    public static boolean isByeString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isByeMsg = msgstr.regionMatches(position,"Type : Bye\n",0,11);
        return isByeMsg;
    }


    public static Bye stringToBye(String msgstr, InetAddress ipsender, InetAddress ipreceiver) {
        int position = 9 + msgstr.indexOf("Pseudo : ");
        int length = msgstr.length();
        char[] dst = new char[length - position];
        msgstr.getChars(position, length, dst, 0);
        String pseudo = new String(dst);
        return new Bye(ipsender,ipreceiver,pseudo);
    }
}