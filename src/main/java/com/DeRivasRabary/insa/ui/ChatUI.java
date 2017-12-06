package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.network.IncomingMessageListener;
import com.DeRivasRabary.insa.network.UDPMessageReceiverManager;
import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

import java.io.InputStreamReader;

public class ChatUI{
    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";


    private final Terminal terminal;
    private final ClavardageNI clavardageNI;

    public ChatUI(Terminal terminal, ClavardageNI clavardageNI) {
        this.terminal = terminal;
        this.clavardageNI = clavardageNI;
    }

    public void principal(){
        boolean open = true;
        ActionChooser actionChooser = new ActionChooser(terminal,this);
        do {
            actionChooser.askActionOn(this);

        }while(open);
    }

    /**
     * Demande à l'utilisateur à quel IP et port envoyer un message
     * TODO : à transformer en paquet
     */
    public void onSend() {
        System.out.print("Destination IP address : ");
        String ipAddress = terminal.readLine();
        System.out.print("Destination port : ");
        String port = terminal.readCommand();
        System.out.print("Message : ");
        String message = terminal.readLine();

        try {
            clavardageNI.onSend(ipAddress, port, message);
            terminal.print(String.format(NOTIFICATION_FORMAT, ipAddress, Integer.valueOf(port)));
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }

    public void onReceive () {
        try {
           clavardageNI.onReceive();
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }

}
