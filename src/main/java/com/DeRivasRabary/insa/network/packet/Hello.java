package com.DeRivasRabary.insa.network.packet;

public class Hello extends PacketManager {

    public Boolean replyRec ;

    public Hello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        super(ipSender, ipReceiver, pseudo);
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


}
