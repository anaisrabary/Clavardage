package com.DeRivasRabary.insa.factory;

import com.DeRivasRabary.insa.network.ReceiverManager;
import com.DeRivasRabary.insa.network.SenderManager;

public class MessageFactory implements MessageServiceFactory{
    @Override
    public SenderManager onSend() {
        return new SenderManager();
    }

    @Override
    public ReceiverManager onReceive() { return new ReceiverManager();
    }

}
