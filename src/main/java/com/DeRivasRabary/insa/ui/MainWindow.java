package com.DeRivasRabary.insa.ui;


import com.DeRivasRabary.insa.controller.Controller;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {
    public static Stage stage;

    public MainWindow(Controller cont) throws Exception {
        this.stage = new Stage();

        ConnexionViewController controller = new ConnexionViewController(this.stage, cont);
        stage.setTitle("toto try");
        stage.setScene(new Scene(controller));
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();

        //Pour quitter proprement l'application (ie tuer tous les threads)
        stage.setOnCloseRequest(e -> {
            if(cont.getState() == Controller.App_State_t.CONNECTED) {
                cont.disconnect();
                Platform.exit();
                System.exit(0);
            }
        });
    }


    public void show() {
        this.stage.show();
    }
}
