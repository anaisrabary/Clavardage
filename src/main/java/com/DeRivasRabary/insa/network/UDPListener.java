package com.DeRivasRabary.insa.network;


import com.DeRivasRabary.insa.model.packet.PacketManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// TODO : Ne doit pas hériter de MessageREceiverService ?? Si mais j'avais oublié !
public abstract class UDPListener extends Thread {
    private static final int BUFFER_SIZE = 1000;
    //private Boolean isRunning = true;
    private final DatagramSocket receiverSocket;


    public UDPListener(DatagramSocket socket){
        this.receiverSocket = socket;
    }

    public void run() {
        byte[] incomingData = new byte[1024];
        while(true) {
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            try {
                receiverSocket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    PacketManager p = (PacketManager) is.readObject();
                    //pas la peine de lire ce qu'on envoie !
                    //if(!p.getAddrSource().equals(ContactCollection.getMe().getIp())) {
                    managePacket(p);
                    //}


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void managePacket(PacketManager p) throws IOException;
    /**
     * Ferme le socket
     * */
    public void close(){

        this.receiverSocket.close();
    }


}
