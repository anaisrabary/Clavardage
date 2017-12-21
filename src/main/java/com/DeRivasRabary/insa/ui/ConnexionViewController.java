package com.DeRivasRabary.insa.ui;


import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.User_Status;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnexionViewController extends BorderPane {

    private Stage stage;
    private Controller controller;
    private User_Status myStatus = User_Status.ONLINE; // par defaut

    @FXML
    public Button buttonConnexion;
    @FXML
    public TextField champPseudo;
    @FXML
    private ChoiceBox status_change_list;



    public boolean pseudoOK = false;

    public String pseudo = "null";

    public ConnexionViewController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("connexionView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        /*status_change_list.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(status_change_list.getItems().get((Integer) number2 ).equals("Disponible")){
                    myStatus = User_Status.ONLINE;
                } else if (status_change_list.getItems().get((Integer) number2 ).equals("Occupé")){
                    myStatus = User_Status.BUSY;
                }
            }
        });*/
        myStatus = User_Status.ONLINE;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == buttonConnexion) {
            controller.connect(champPseudo.getText());
            controller.changeStatus(myStatus.toString());
            System.out.println("connexion");
            Stage stage = (Stage) buttonConnexion.getScene().getWindow();
            ContactViewController contactController = new ContactViewController(stage, controller);
            stage.setResizable(false);
            stage.setHeight(800);
            stage.setWidth(500);
            stage.setScene(new Scene(contactController));
            stage.show();
        }
    }


    public boolean ipseudoOK() { return pseudoOK; }


}