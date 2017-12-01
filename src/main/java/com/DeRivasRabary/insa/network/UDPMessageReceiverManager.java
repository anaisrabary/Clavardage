package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMessageReceiverManager implements MessageReceiverService {
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(port);
        DatagramPacket receiverPacket = new DatagramPacket(new byte[1000],1000); // TEst
        receiverSocket.receive(receiverPacket);
        byte[] data = receiverPacket.getData();
        String dataText= new String(data);
        System.out.println(dataText);
        // throw new UnsupportedOperationException();
    }
}
