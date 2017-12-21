package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.UserList;
import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.User_Status;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ContactViewController extends BorderPane {


    @FXML
    protected Label pseudo_label;
    @FXML
    protected ListView online_contacts;
    @FXML
    protected ChoiceBox<String> status_change_list; //


    private Stage stage;
    private Controller controller;

    public ContactViewController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Contactwindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    private void handleListAction(MouseEvent event) {
        try {
            ChatSessionView view = ViewController.getInstance().getView((User) online_contacts.getSelectionModel().getSelectedItem(), true);
            view.requestFocus();
        } catch (NullPointerException e) {}
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserList users = UserList.getInstance();
        pseudo_label.setText(UserList.getMoi().getPseudo());

        // statut initial
        switch (User_Status.valueOf(UserList.getMoi().getStatus().toString())) {
            case ONLINE:
                status_change_list.getSelectionModel().select(0);
                break;
            case BUSY:
                status_change_list.getSelectionModel().select(2);
                break;
        }

        // Changement de statut
        status_change_list.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
            String status = status_change_list.getItems().get((Integer) number2);
            status = status.substring(1, status.length()-1); // TODO : Utile ?
            controller.changeStatus(status);
        });

        // Liste des contacts
        ObservableList<User> contactObservableList = users.getUserlist();
        users.setAddCallback(contact -> Platform.runLater(() -> contactObservableList.add(contact)));
        users.setDelCallback(contact -> Platform.runLater(() -> contactObservableList.remove(contact)));
        online_contacts.setItems(contactObservableList);

        online_contacts.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(ListView<User> p) {
                ListCell<User> cell = new ListCell<User>() {

                    /*@Override
                    protected void updateItem(User u, boolean bln) {
                        super.updateItem(u, bln);
                        if (u != null) {
                            switch(u.getStatus()){
                                case ONLINE:
                                    Platform.runLater(() -> setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/images/connected.png")))));
                                    break;
                                case BUSY:
                                    Platform.runLater(() -> setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/images/busy.png")))));
                                    break;
                            }

                            Platform.runLater(() -> setText(c.getPseudo() + (c.getMessage_perso().equals("") ? "" : " - " + c.getMessage_perso())));
                        } else {
                            Platform.runLater(() -> setText(""));
                            Platform.runLater(() -> setGraphic(null));
                        }
                    }*/
                };
                return cell;
            }
        });
    }




}