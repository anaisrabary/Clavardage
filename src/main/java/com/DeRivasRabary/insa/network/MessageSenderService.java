package com.DeRivasRabary.insa.network;

import com.DeRivasRabary.insa.network.packet.PacketManager;

public interface MessageSenderService {
    void sendMessageOn(PacketManager packetManager) throws Exception;
}
