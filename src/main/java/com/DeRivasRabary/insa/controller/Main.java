package com.DeRivasRabary.insa.controller;

import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.ui.MainGUIFX;
import com.DeRivasRabary.insa.ui.ViewController;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        Controller controller = new Controller(); //instantiation du contrôleur
        ClavardageNI.createInstance();
        ClavardageNI.getInstance().setController(controller);

        //On passe le contrôleur principal au contrôleur de vues pour que les vues de chat y ait accès
        ViewController.createInstance();
        ViewController viewController = ViewController.getInstance();
        viewController.setController(controller);

        //Lancement interface graphique
        //On passe le contrôleur aux vues de bases (ie toutes les fenêtres hors chat)
        MainGUIFX.SetController(controller);
        MainGUIFX.launch(MainGUIFX.class);
    }
}
