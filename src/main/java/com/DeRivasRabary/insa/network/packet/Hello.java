package com.DeRivasRabary.insa.network.packet;

public class Hello extends PacketManager {

    public Boolean replyRec ;

    public Hello(String ipSender, String ipReceiver, String pseudo, Boolean replyRec){
        super(ipSender, ipReceiver, pseudo);
        this.replyRec = replyRec ;
    }


}
