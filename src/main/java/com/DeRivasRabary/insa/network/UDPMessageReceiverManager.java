package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMessageReceiverManager implements MessageReceiverService {
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(port);

        while(true){
            DatagramPacket receiverPacket = new DatagramPacket(new byte[1000],1000); // TEst
            receiverSocket.receive(receiverPacket);
            byte[] data = receiverPacket.getData();

            incomingMessageListener.onNewIncomingMessage(new String(data));

        }

    }
}
