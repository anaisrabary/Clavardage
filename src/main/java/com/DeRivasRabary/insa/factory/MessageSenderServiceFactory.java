package com.DeRivasRabary.insa.factory;

import com.DeRivasRabary.insa.network.MessageSenderService;
import com.DeRivasRabary.insa.network.TCPMessageSenderManager;
import com.DeRivasRabary.insa.network.UDPMessageSenderManager;

public class MessageSenderServiceFactory implements MessageServiceFactory<MessageSenderService> {

    @Override
    public MessageSenderService onTCP() {
        return new TCPMessageSenderManager();
    }

    @Override
    public MessageSenderService onUDP() {
        return new UDPMessageSenderManager();
    }
}
