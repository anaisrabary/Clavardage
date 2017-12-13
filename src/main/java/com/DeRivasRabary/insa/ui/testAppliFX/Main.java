package com.DeRivasRabary.insa.ui.testAppliFX;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      /* code de l'appli FX
        * 1. prepare the scene graphe avec les require nodes
        * 2. prepare the scene avec les dimensions and add the scene graph (root node of the scene graph)
        * 3. prepare a stage and add the scene to the stage and display the contents of the stage*/

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));



        Group mainGroup = new Group();
        primaryStage.setTitle("Chat-System Main Window ");
        primaryStage.setScene(mainWindow(mainGroup));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public Scene mainWindow(Group group){
        Text text = new Text();
        text.setFont(new Font(30));
        text.setX(10);
        text.setY(50);
        text.setText("You are here !");

        ObservableList list = group.getChildren();

        list.add(text);

        Scene mainScene = new Scene(group, 1200,800);

        return mainScene ;

    }
}
