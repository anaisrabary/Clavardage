package com.DeRivasRabary.insa.network.packet;

public class PacketManager {

    public String ipSender ;
    public String ipReceiver ;
    // rajouter un horodatage


    /**
     * Constructeur de paquet commun à hello-bye-message
     * @param ipSender
     * @param ipReceiver
     */
    public PacketManager(String ipSender, String ipReceiver){
        this.ipSender = ipSender ;
        this.ipReceiver = ipReceiver ;
    }


}
