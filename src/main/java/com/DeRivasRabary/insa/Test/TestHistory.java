package com.DeRivasRabary.insa.Test;

import com.DeRivasRabary.insa.History.HistoryManager;
import com.DeRivasRabary.insa.model.packet.Message;

import java.io.IOException;
import java.net.InetAddress;

public class TestHistory {
    //TODO : faire des tests unitaires


    public static void main(String[] args) throws IOException {

        HistoryManager localHistory = new HistoryManager("127.0.0.1");
        Message message = new Message(InetAddress.getByName("127.0.0.0"),InetAddress.getByName("127.0.0.1"),
                "Emetteur","Recepteur","Voici le message a sauvegarder");
        Message message1 = new Message(InetAddress.getByName("127.0.0.1"),InetAddress.getByName("127.0.0.0"),
                "Recepteur","Emetteur","2e message à sauvegarder");
        localHistory.addMessage(message);
        localHistory.addMessage(message1);
    }
}
