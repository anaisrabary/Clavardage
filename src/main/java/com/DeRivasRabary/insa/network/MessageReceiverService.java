package com.DeRivasRabary.insa.network;

public interface MessageReceiverService {

    /**
     * Listen to incoming messages on given port, and notifies the listener of new messages
     *
     * @param port                    The port to listen on
     * @param incomingMessageListener The listener to notify on new incoming messages
     */
    void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception;
}
