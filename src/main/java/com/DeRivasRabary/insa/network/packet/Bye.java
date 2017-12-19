package com.DeRivasRabary.insa.network.packet;

import static com.DeRivasRabary.insa.network.packet.TypePacket.BYE;

public class Bye extends PacketManager {


    /**
     * Constructeur du Bye pour signaler à tout le monde qu'on s'en va
     * Utiliser la classe PacketFactory pour construire un paquet
     *
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     */
    public Bye(String ipSender, String ipReceiver, String pseudo) {
        super(ipSender, ipReceiver, pseudo, BYE);
    }

    @Override
    public String toString() {
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Bye\n";
        String ipsender = "IPSource : " + this.ipSender + "\n";
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n";
        return delimiter + type + ipsender + ipreceiver + date + pseudo + delimiter;
    }

    public String toDisplaySend() {
        String type = "Type : Bye\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n";
        return type + date + pseudo;
    }


    public static boolean isByeString(String msgstr) {
        int position = msgstr.indexOf("Type :");
        boolean isByeMsg = msgstr.regionMatches(position,"Type : Bye\n",0,11);
        return isByeMsg;
    }


    public static Bye stringToBye(String msgstr, String ipsender, String ipreceiver) {
        int position = 9 + msgstr.indexOf("Pseudo : ");
        int length = msgstr.length();
        char[] dst = new char[length - position];
        msgstr.getChars(position, length, dst, 0);
        String pseudo = new String(dst);
        return new Bye(ipsender,ipreceiver,pseudo);
    }
}