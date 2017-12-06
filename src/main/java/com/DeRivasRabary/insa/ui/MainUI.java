package com.DeRivasRabary.insa.ui;

import java.util.StringJoiner;

import com.DeRivasRabary.insa.ui.infrastructure.Terminal;


public class MainUI {

    private static final String CONNECT = "connect";
    private static final String DISCONNECT = "disconnect";
    private static final String OPENNING_SESSION ="open";
    private static final String CLOSING_SESSION = "close";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PROMPT = "Enter your choice: ";
    private static final String POSSIBLE_ACTIONS = new StringJoiner(LINE_SEPARATOR).add("What do you wanna do?")
            .add(CONNECT + "> Connect to clavardage")
            .add(DISCONNECT + "> Disconnect from clavardage")
            .add(OPENNING_SESSION + "> Open a session with the user of your choice")
            .add(CLOSING_SESSION + "> Close a session with the user of your choice")
            .toString();


    private final Terminal terminal;
    private final ChatUI chatUI ;
    //private final ConnectingUI connectingUI ;

    public MainUI(Terminal terminal, ChatUI chatUI) {
        this.terminal = terminal;
        this.chatUI = chatUI;
    }

    public void askForAction() {
        terminal.print(POSSIBLE_ACTIONS);
        terminal.print(PROMPT);
        String choice = terminal.readCommand();
        switch (choice) {
            case CONNECT:
                //connectingUI.connect();
                break;
            case DISCONNECT:
               // connectingUI.disconnect();
                break;
            case OPENNING_SESSION:
                chatUI.oppeningSession();
                break;
            case CLOSING_SESSION:
                break;

        }
    }
}
