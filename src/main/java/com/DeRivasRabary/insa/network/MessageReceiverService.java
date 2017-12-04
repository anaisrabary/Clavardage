package com.DeRivasRabary.insa.network;

public interface MessageReceiverService {

    void listenOnPOrt( int port, IncomingMessageListener incomingMessageListener) throws Exception;
}
