package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.network.ClavardageNI;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.UnknownHostException;

//import static java.lang.Thread.sleep;

public class MainGUIFX extends Application {


   private static Controller controller;

    public void start(Stage primaryStage) throws Exception {
        MainWindow mainwind ;
        mainwind = new MainWindow(controller);
        mainwind.show();
    }

    public static void SetController(Controller c){ controller = c;}

}