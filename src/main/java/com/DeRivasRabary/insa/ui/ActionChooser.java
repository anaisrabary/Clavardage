package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.ui.infrastructure.Terminal;

public class ActionChooser {

    private static final String SEND  = "send";
    private static final String RECEIVE = "receive";
    private final Terminal terminal;
    private final ChatUI chatUI;

    public ActionChooser(Terminal terminal, ChatUI chatUI) {
        this.terminal = terminal;
        this.chatUI = chatUI;
    }

    /**
     * Demande à l'utilisateur si il veut envoyer ou recevoir un message et execute la bonne commande.
     * TODO : dans chaque case faire un do while pour rester dans le meme état tant qu'on a pas appuyé sur une touche par exemple
     * @param chatUI
     */
    public void askActionOn(ChatUI chatUI) {
        System.out.print("Choose your action [send - receive] : ");
        String choice = terminal.readCommand();
        switch (choice) {
        case SEND:
            chatUI.onSend();
            break;
        case RECEIVE:
            chatUI.onReceive();
            break;
        }
    }
}
