package com.DeRivasRabary.insa.network.packet;


/**
 * Classe message hérite de packetManager et construit un message pour une conversation
 */
public  class Message extends PacketManager{

    public String message ;

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

    @Override
    public String toString(){
        String delimiter = "*************************\n"; // 25 *
        String type = "Type : Message";
        String ipsender = "IPSource : " + this.ipSender + "\n" ;
        String ipreceiver = "IPDestination : " + this.ipReceiver + "\n";
        String pseudo = "Pseudo : " + this.pseudo + "\n" ;
        String message = "Message : " + this.message +"\n" ;
        return delimiter + type + ipsender + ipreceiver + pseudo + message + delimiter ;
    }

}
