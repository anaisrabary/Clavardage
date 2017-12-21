package com.DeRivasRabary.insa.ui;


import com.DeRivasRabary.insa.controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {
    public static Stage stage;
    public ConnexionViewController conViewCtrl;
    public Controller controller;

    public MainWindow(Controller cont) throws IOException {
        this.stage = new Stage();
        controller = cont ;

        ConnexionViewController.createInstance(this.stage, controller);
        conViewCtrl = ConnexionViewController.getInstance();
        stage.setTitle("toto try");
        stage.setScene(new Scene(conViewCtrl));
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);

        //Pour quitter proprement l'application (ie tuer tous les threads)
        stage.setOnCloseRequest(e -> {
            if(controller.getState() == Controller.App_State_t.CONNECTED) {
                controller.disconnect();
                Platform.exit();
                System.exit(0);
            }
        });
    }


    public void show() {
        this.stage.show();
    }

}
