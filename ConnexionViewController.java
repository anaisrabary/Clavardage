package com.DeRivasRabary.insa.ui;


import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.UserList;
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

    private static ConnexionViewController instance;
    private Stage stage;
    private Controller controller;
    private User_Status myStatus = User_Status.ONLINE; // par defaut

    @FXML
    private TextField pseudo;
    @FXML
    private Button connect_btn;
    @FXML
    private ChoiceBox status_change_list;


    public static ConnexionViewController createInstance(Stage stage, Controller ctrl){
        if (instance == null) {
            instance = new ConnexionViewController(stage, ctrl);
        }
        return instance;
    }

    public static ConnexionViewController getInstance() {
        return instance;
    }

    public ConnexionViewController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/DeRivasRabary/insa/ui/connexionView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
             loader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }



        status_change_list.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if(status_change_list.getItems().get((Integer) number2 ).equals("Disponible")){
                    myStatus = User_Status.ONLINE;
                } else if (status_change_list.getItems().get((Integer) number2 ).equals("Occupé")){
                    myStatus = User_Status.BUSY;
                }
            }
        });
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == connect_btn) {
            controller.connect(pseudo.getText());
            controller.changeStatus(myStatus.toString());
            UserList.createInstance();
            System.out.println("connexion");
            Stage stageBis = (Stage) connect_btn.getScene().getWindow();
            ContactViewController contactController = new ContactViewController(stageBis, controller);
            stageBis.setResizable(false);
            stageBis.setHeight(800);
            stageBis.setWidth(500);
            stageBis.setScene(new Scene(contactController));
            stageBis.show();
        }
    }



}