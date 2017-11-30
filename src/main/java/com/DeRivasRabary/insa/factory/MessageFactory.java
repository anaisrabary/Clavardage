package com.DeRivasRabary.insa.factory;

public class MessageFactory {
    @Override
    public MessageService onSend() {
        return new MessageService();
    }

    @Override
    public MessageService onReceive() {
        return new MessageService();
    }

}
