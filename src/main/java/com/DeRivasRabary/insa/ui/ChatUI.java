package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.infrastructure.Terminal;
import com.DeRivasRabary.insa.factory.MessageFactory;
import com.DeRivasRabary.insa.network.ReceiverManager;
import com.DeRivasRabary.insa.network.SenderManager;

public class ChatUI implements CommunicationUI {

    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";
    private  static final int PORT = 1234;

    private final Terminal terminal;
    private final MessageFactory messageFactory;
    private

    public ReceiveUI(Terminal terminal, MessageFactory messageReceiverServiceFactory) {
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

    private void ReceiveOnPort(ReceiverManager receiverManager) {
        try {
            receiverManager.listenOnPort(PORT, this);
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }
    private void sendMessageWith(MessageSenderService messageSenderService) {
        System.out.print("Destination IP address : ");
        String ipAddress = terminal.readLine();
        System.out.print("Destination port : ");
        int port = terminal.readInt();
        System.out.print("Message : ");
        String message = terminal.readLine();
        try {
            messageSenderService.sendMessageOn(ipAddress, port, message);
            terminal.print(String.format(NOTIFICATION_FORMAT, ipAddress, port));
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
