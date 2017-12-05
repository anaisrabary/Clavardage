package com.DeRivasRabary.insa.network.packet;

public class Bye extends PacketManager {



    public Bye(String ipSender, String ipReceiver, String pseudo){
        super(ipSender, ipReceiver,pseudo);
    }

    @Override
    public String toString(){
        String delimiter = "******************************\n"; // 30 *
        String type = "Type : Bye\n";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String date = "Date : " + this.date + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        return delimiter+ type + ipsender + ipreceiver + date + pseudo + delimiter ;
    }

}
