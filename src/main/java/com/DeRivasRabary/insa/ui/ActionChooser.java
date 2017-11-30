package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

public class ActionChooser {

    private static final String SEND  = "send";
    private static final String RECEIVE = "receive";
    private final Terminal terminal;

    public ActionChooser(Terminal terminal) {
        this.terminal = terminal;
    }

    public void askActionOn(CommunicationUI communicationUI) {
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
