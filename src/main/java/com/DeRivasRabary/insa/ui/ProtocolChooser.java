package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.Infrastructure.terminal.Terminal;

public class ProtocolChooser {

    private static final String SEND  = "send";
    private static final String RECEIVE = "receive";
    private final Terminal terminal;

    public ProtocolChooser(Terminal terminal) {
        this.terminal = terminal;
    }

    public void askProtocolOn(CommunicationUI communicationUI) {
        System.out.print("Choose your action [send - receive] : ");
        String choice = terminal.readCommand();
        switch (choice) {
        case SEND:
            communicationUI.onSend();
            break;
        case RECEIVE:
            communicationUI.onReceive();
            break;
        }
    }
}
