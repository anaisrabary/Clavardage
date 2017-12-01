package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

public class ChatUI implements CommunicationUI {

    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";
    private  static final int PORT = 1234;

    private final Terminal terminal;
    private final MessageFactory messageFactory;

    public ChatUI(Terminal terminal, MessageFactory messageReceiverServiceFactory) {
        this.terminal = terminal;
        this.messageFactory = messageReceiverServiceFactory;
    }

    @Override
    public void onSend() {
        sendMessageWith(messageFactory.onSend());
    }

    @Override
    public void onReceive() {
        receiveOnPort(messageFactory.onReceive());
    }

    private void receiveOnPort(ReceiverManager receiverManager) {
        try {
            receiverManager.listenOnPort(PORT, this);
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }
    private void sendMessageWith(SenderManager senderManager) {
        System.out.print("Destination IP address : ");
        String ipAddress = terminal.readLine();
        System.out.print("Destination port : ");
        String port = terminal.readCommand();
        System.out.print("Message : ");
        String message = terminal.readLine();
        try {
            senderManager.sendMessageOn(ipAddress, port, message);
            terminal.print(String.format(NOTIFICATION_FORMAT, ipAddress, port));
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }
/*
    @Override
    public void onNewIncomingMessage(String message) {
        terminal.print("New incoming message: " + message);
    }
    }
    */

}
