package com.DeRivasRabary.insa.network.packet;


/**
 * Classe message hérite de packetManager et construit un message pour une conversation
 */
public  class Message extends PacketManager{

    String message ;

    /**
     * Constructeur de message
     * @param ipSender
     * @param ipReceiver
     * @param pseudo
     * @param message
     */
    public Message(String ipSender, String ipReceiver, String pseudo, String message){
        super(ipSender, ipReceiver, pseudo);
        this.message = message ;
    }

}
