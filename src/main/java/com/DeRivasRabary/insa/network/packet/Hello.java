package com.DeRivasRabary.insa.network.packet;

import static com.DeRivasRabary.insa.network.packet.TypePacket.HELLO;

public class Hello extends PacketManager {

    public Boolean replyRec ;

    /**
     * Constructeur d'un Hello pour dire aux autres qu'on est sur le réseau
     * Utiliser la classe PacketFactory pour construire un paquet
     * Si replyRec est vrai on demande à tous les récepteurs de nous envoyer un hello en retour
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param replyRec
     */
    public Hello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        super(ipSender, ipReceiver, pseudo, HELLO);
        this.replyRec = replyRec ;
    }

    @Override
    public String toString(){
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Hello\n";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String replyrec = "ReplyRec : " + Boolean.toString(this.replyRec) +"\n" ;
        return delimiter + type + ipsender + ipreceiver + date + pseudo + replyrec + delimiter ;
    }

    public String toDisplaySend(){
        String type = "Type : Hello\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String replyrec = "ReplyRec : " + Boolean.toString(this.replyRec) +"\n" ;
        return type + date + pseudo + replyrec;
    }

}
