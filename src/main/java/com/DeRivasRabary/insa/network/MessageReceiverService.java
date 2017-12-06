package com.DeRivasRabary.insa.network;

public interface MessageReceiverService {


    void listenOnPort( int port, IncomingMessageListener incomingMessageListener) throws Exception;
}
