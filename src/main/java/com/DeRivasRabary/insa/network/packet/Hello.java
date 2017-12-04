package com.DeRivasRabary.insa.network.packet;

public class Hello extends PacketManager {

    public Boolean replyRec ;

    public Hello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        super(ipSender, ipReceiver, pseudo);
        this.replyRec = replyRec ;
    }

    @Override
    public String toString(){
        String delimiter = "*************************\n"; // 25 *
        String type = "Type : Hello";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String replyrec = "ReplyRec : " + Boolean.toString(this.replyRec) +"\n" ;
        return delimiter + type + ipsender + ipreceiver + pseudo + replyrec + delimiter ;
    }


}
