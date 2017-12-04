package com.DeRivasRabary.insa.network;

public interface MessageSenderService {
    void sendMessageOn(String ipAddress, String port, String message) throws Exception;
}
