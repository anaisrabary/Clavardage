package com.DeRivasRabary.insa.ui.testAppliFX;

import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.user.LocalUser;
import com.DeRivasRabary.insa.user.User;
import com.DeRivasRabary.insa.user.UserList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

//import static java.lang.Thread.sleep;

public class MainGUIFX extends Application {
    private Stage primaryStage;
    private Stage connexionStage;
    //Pour tester sur un seul PC : laisser les ports comme ça pour lancer la première fenêtre et les échanger avant de lancer la seconde !
   /* private int portEcouteUDP = 5550;
    private int portEcouteTCP = 4440;
    private int portEnvoiUDP = 5551;
    private int portEnvoiTCP = 4441;
    */

    private Thread gestionApp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        ClavardageNI.createInstance();
        UserList.createInstance();

        this.primaryStage=primaryStage;
        this.connexionStage=new Stage();

        initConnexion();
        initLayout();

        connexionStage.show();


        gestionApp = new Thread ( () -> launchMainApp() );

        gestionApp.start();

    }

    public void initConnexion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ConnectionPanel.fxml"));
            connexionStage.setTitle("Chat System connect");
            connexionStage.setScene(new Scene(root, 400, 200));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initLayout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUIControllerFX.fxml"));
            Platform.runLater( ( () -> primaryStage.setTitle("Chat System  --- Main")));
            Platform.runLater( ( () ->primaryStage.setScene(new Scene(root, 850, 400))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApp() {
        try {
            ClavardageNI.getInstance().stop();
            System.out.println("Processus terminé.");
            gestionApp.interrupt();
            Runtime.getRuntime().exit(0);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public void launchMainApp() {
        ClavardageNI.getInstance().onConnect();
        boolean done = false;
        while (!done) {
            if (GUIConnexion.getInstance().pseudoOK) {
                Platform.runLater( ( () -> connexionStage.hide()));
                Platform.runLater( ( () -> primaryStage.show()));
                done=true;
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        User moi = new LocalUser(GUIConnexion.getInstance().pseudo, ClavardageNI.getLocalAdress(), true);
        initLayout();
        GUIControllerFX.getInstance().setPseudoUser(moi.getPseudo());
        UserList.getInstance().addUser(moi);
        // pour le test !
        UserList.getInstance().addUser(new User("tutu","123.123.123.123"));
        UserList.getInstance().addUser(new User("titi","205.205.205.205"));
        UserList.getInstance().addUser(new User("tata","168.192.100.100"));
        UserList.getInstance().addUser(new User("tete","10.10.10.10"));
        GUIControllerFX.getInstance().updateContacts();

        try {
            ClavardageNI.getInstance().go();
        }catch (Exception e ){

        }

        primaryStage.setOnCloseRequest( (WindowEvent e) -> closeApp() );
    }



}