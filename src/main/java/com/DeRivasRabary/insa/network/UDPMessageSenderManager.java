package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMessageSenderManager implements MessageSenderService{

    /**
     * Envoie un message à l'adresse IP et au port donné
     * Temporraire car on va envoyer des paquets ?
     * TODO : TODO modifier l'argument message par paquet NON ?
     * @param ipAddress
     * @param port
     * @param message
     * @throws Exception
     */
    @Override
    public void sendMessageOn(String ipAddress, String port, String message) throws Exception {
        DatagramSocket senderSocket = new DatagramSocket();
        byte[] data = message.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setAddress(InetAddress.getByName(ipAddress));
        datagramPacket.setPort(Integer.valueOf(port));
        senderSocket.send(datagramPacket);
        senderSocket.close();

      //  throw new UnsupportedOperationException();
    }

}
