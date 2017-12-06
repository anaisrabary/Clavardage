package com.DeRivasRabary.insa.network;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

// TODO : Ne doit pas hériter de MessageREceiverService ?? Si mais j'avais oublié !
public class UDPMessageReceiverManager implements MessageReceiverService{
    private static final int BUFFER_SIZE = 1000;
    //private Boolean isRunning = true;
    private final DatagramSocket receiverSocket;

    private final int port ;


    public UDPMessageReceiverManager (int port)throws Exception{
        this.port = port;
        this.receiverSocket = new DatagramSocket(this.port);
    }


    /**
     * Lance l'écoute sur un port
     * @param port
     * @param incomingMessageListener
     * @throws Exception
     */
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        DatagramPacket receiverPacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
        receiverSocket.receive(receiverPacket);
        byte[] data = receiverPacket.getData();
        incomingMessageListener.onNewIncominMessage(new String(data).trim());

    }

    /**
     * Ferme le socket
     * */
    public void close(){

        this.receiverSocket.close();
    }


}
