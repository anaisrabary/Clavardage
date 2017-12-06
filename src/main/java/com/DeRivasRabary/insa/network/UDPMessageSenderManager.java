package com.DeRivasRabary.insa.network;


import com.DeRivasRabary.insa.network.packet.PacketManager;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMessageSenderManager implements MessageSenderService{

    private static final int PORT = 1234; // TODO : générer un port différent pour chaque UI
    /**
     * Envoie un message à l'adresse IP et au port donné
     * Temporraire car on va envoyer des paquets ?
     * @param packet
     * @throws Exception
     */
    @Override
    public void sendMessageOn(PacketManager packet) throws Exception {
        DatagramSocket senderSocket = new DatagramSocket();
        byte[] data = packet.toString().getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setAddress(InetAddress.getByName(packet.getIpReceiver()));
        datagramPacket.setPort(Integer.valueOf(PORT));
        senderSocket.send(datagramPacket);
        senderSocket.close();

      //  throw new UnsupportedOperationException();
    }

}
