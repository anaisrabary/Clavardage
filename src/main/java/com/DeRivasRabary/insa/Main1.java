package com.DeRivasRabary.insa;


import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.ui.ActionChooser;
import com.DeRivasRabary.insa.ui.ChatUI;
import com.DeRivasRabary.insa.ui.MainUI;
import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main1
{

    public static void main(String[] args) throws IOException {
        Terminal terminal = new Terminal();
        ChatUI chatUI = new ChatUI(terminal, new ClavardageNI());
        MainUI mainUI = new MainUI(terminal, chatUI);
        mainUI.askForAction();
        //StartingUI startingUI = new StartingUI(terminal, new ActionChooser(terminal), receiveUI, sendUI);
        //startingUI.askForAction();

    }


}







