package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.User;
import javafx.application.Platform;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class ViewController {

    public enum Update_type {
        NEW_MESSAGE,
        NEW_MESSAGE_ME,
        STATUS_CHANGE, // disponible / occupé
        NOT_EDITABLE
    }

    private HashMap<String, ChatSessionView> viewMap;
    private Controller controller;
    private static ViewController instance ;


    public static ViewController createInstance(){
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }

    public static ViewController getInstance() {
        return instance;
    }

    private ViewController() {
        this.viewMap = new HashMap<>();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Retourne la fenêtre de session avec le contact..
     * Si la fenêtre n'existe pas, création de la fenêtre et on la stocke dans la hashmap
     * @param user
     * @param graphicThread
     * @return ChatWindow
     */
    public ChatSessionView getView(User user, boolean graphicThread) {
        /*
         * Pour résoudre le problème concurrent suivant :  il faut attendre que le thread graphique ait crée la fenêtre
         * et qu'il nous renvoie son instance avant de continuer, on utilise le countdownlatch
         * Si on est dans le thread graphique, inutile, cela crée un interbloquage, d'où le booléen
         */
        final CountDownLatch latch = new CountDownLatch(1);
        if(viewMap.get(user.getPseudo()) == null)
        {
            //on demande au thread graphique de la créer
            Platform.runLater(() -> {
                try {
                    ChatSessionView view = new ChatSessionView(controller, user);
                    viewMap.put(user.getPseudo(), view);
                    //on rafraîchit le statut pour nous et le contact distant
                    view.getChatWindowController().refreshStatus(true);
                    view.getChatWindowController().refreshStatus(false);
                    view.show();
                    if(!graphicThread) {
                        latch.countDown(); //on notifie que c'est fait
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                if(!graphicThread) {
                    latch.await(); //attente de la notification du thread graphique
                }
                System.out.println("okay attente");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return viewMap.get(user.getPseudo());
    }

    /**
     * Delete the view, called when a contact gets offline
     *
     * @param user
     */
    public void delView(User user) {
        if(viewExists(user)) {
            viewMap.remove(user.getPseudo());
        }
    }

    public void updateView(ChatSessionView view, Update_type type, String toUpdate) {
        switch (type) {
            case NEW_MESSAGE:
                view.getChatWindowController().addMessage(false, toUpdate);
                break;
            case NEW_MESSAGE_ME:
                view.getChatWindowController().addMessage(true, toUpdate);
                break;
            case STATUS_CHANGE:
                System.out.println("changement dans la vue");
                view.getChatWindowController().refreshStatus(false);
                break;
            case NOT_EDITABLE:
                view.getChatWindowController().getOffline();
                break;
        }
    }

    public boolean viewExists(User user) {
        return viewMap.get(user.getPseudo()) != null;
    }

    public HashMap<String, ChatSessionView> getAllViews() {
        return viewMap;
    }



}
