package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;

import javafx.application.Application;

import javafx.stage.Stage;


public class MainGUIFX extends Application {

   private static Controller controller;

    public void start(Stage primaryStage) throws Exception {


        MainWindow mainwind = new MainWindow(controller);
        mainwind.show();

    }

    public static void SetController(Controller c){ controller = c;}

}