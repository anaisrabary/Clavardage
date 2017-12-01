package com.DeRivasRabary.insa.factory;

import com.DeRivasRabary.insa.network.packet.*;

public class PacketFactory {



    public static PacketManager createPackage(String ipSender, String ipReceiver, PacketManager packetManager){


        if (Message.class==packetManager.getClass()){

        }
        else if (Hello.class==packetManager.getClass()){

        }
        else if (Bye.class==packetManager.getClass()){

        }
    }
}
