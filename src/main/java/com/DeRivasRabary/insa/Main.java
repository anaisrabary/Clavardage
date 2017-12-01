package com.DeRivasRabary.insa;


import com.DeRivasRabary.insa.factory.MessageReceiverServiceFactory;
import com.DeRivasRabary.insa.factory.MessageSenderServiceFactory;
import com.DeRivasRabary.insa.ui.ActionChooser;
import com.DeRivasRabary.insa.ui.ChatUI;
import com.DeRivasRabary.insa.ui.CommunicationUI;
import com.DeRivasRabary.insa.ui.MainUI;
import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main(String[] args) throws IOException {
        Terminal terminal = new Terminal();
        ChatUI chatUI = new ChatUI(terminal, new MessageReceiverServiceFactory());
        MainUI mainUI = new MainUI(terminal,);
        mainUI.askForAction();
        StartingUI startingUI = new StartingUI(terminal, new ActionChooser(terminal), receiveUI, sendUI);
        startingUI.askForAction();
    }
}







