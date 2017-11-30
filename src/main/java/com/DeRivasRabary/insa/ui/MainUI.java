package com.DeRivasRabary.insa.ui;

import java.util.StringJoiner;
import com.DeRivasRabary.insa.Infrastructure.terminal.Terminal;


public class MainUI {

    private static final int CONNECT = "connect";
    private static final int DISCONNECT = "disconnect";
    private static final int OPENNING_SESSION ="opensession";
    private static final int CLOSING_SESSION = "closingsession";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PROMPT = "Enter your choice: ";
    private static final String POSSIBLE_ACTIONS = new StringJoiner(LINE_SEPARATOR).add("What do you wanna do?")
            .add(CONNECT + "> Connect to clavardage")
            .add(DISCONNECT + "> Disconnect from clavardage")
            .add(OPENNING_SESSION + "> Open a session with the user of your choice")
            .add(CLOSING_SESSION + "> Close a session with the user of your choice")
            .toString();


    private final Terminal terminal;
    private final ProtocolChooser protocolChooser;
    private final CommunicationUI receiveUI;
    private final CommunicationUI sendUI;

    public StartingUI(Terminal terminal, ProtocolChooser protocolChooser, ChatUI chatUI,) {
        this.terminal = terminal;
        this.protocolChooser = protocolChooser;
        this.receiveUI = receiveUI;
        this.sendUI = sendUI;
    }

    public void askForAction() {
        terminal.print(POSSIBLE_ACTIONS);
        terminal.print(PROMPT);
        int choice = terminal.readCommand();
        switch (choice) {
            case RECEIVE:
                protocolChooser.askProtocolOn(receiveUI);
                break;
            case SEND:
                protocolChooser.askProtocolOn(sendUI);
                break;
        }
    }
}
