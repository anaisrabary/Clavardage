package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.Infrastructure.terminal.Terminal;
import com.DeRivasRabary.insa.factory.MessageFactory;

public class ChatUI {

    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";

    private final Terminal terminal;
    private final MessageFactory messageFactory;
    private

    public ReceiveUI(Terminal terminal, MessageFactory messageReceiverServiceFactory) {
        this.terminal = terminal;
        this.messageFactory = messageReceiverServiceFactory;
    }

    @Override
    public void onUDP() {
        askForPort(messageReceiverServiceFactory.onSend());
    }

    @Override
    public void onTCP() {
        askForPort(messageReceiverServiceFactory.onReceive());
    }

    private void askForPort(MessageReceiverService messageReceiverService) {
        System.out.print("Enter the port to listen on: ");
        int port = terminal.readInt();
        try {
            messageReceiverService.listenOnPort(port, this);
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }

    @Override
    public void onNewIncomingMessage(String message) {
        terminal.print("New incoming message: " + message);
    }
}

}
