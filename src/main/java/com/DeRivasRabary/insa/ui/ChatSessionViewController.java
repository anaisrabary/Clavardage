package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ChatSessionViewController extends BorderPane implements Initializable {
    private Stage stage;
    private Controller controller;
    private User user;

    /**
     *  String getDate()  dans packet manager
     * @return
     */

    private SimpleDateFormat date_format;
    private SimpleDateFormat heure_format;


    private boolean was_me; //optimisation graphique, on ne répète pas le nom de la personne si plusieurs message à la suite
    private boolean was_dialog;

    private boolean offline;


    @FXML
    protected VBox box_type_message;
    @FXML
    protected TextFlow messages_received;
    @FXML
    protected TextArea message_write;
    @FXML
    protected ScrollPane messages_received_pane;
    @FXML
    protected Label pseudo_label;
    @FXML
    protected Label status_label;
    @FXML
    protected Label last_message_date_label;
    @FXML
    protected Button btn_fichiers; //




    public ChatSessionViewController(Stage stage, Controller controller, User user) {
        this.stage = stage;
        this.controller = controller;
        this.user = user;
        date_format = new SimpleDateFormat("dd/MM/yyyy");
        heure_format = new SimpleDateFormat("HH:mm");

        was_me = true;
        was_dialog = false;
        offline = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatWindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Pour gérer le redimensionnement de la fenêtre
        VBox.setVgrow(messages_received, Priority.ALWAYS);
        HBox.setHgrow(box_type_message, Priority.ALWAYS);

        //Pour scroller automatiquement en bas du scrollpane
        messages_received.getChildren().addListener(
                (ListChangeListener<Node>) ((change) -> {
                    messages_received.layout();
                    messages_received_pane.layout();
                    messages_received_pane.setVvalue(1.0f);
                })
        );

        //Gestion de la fermeture de la fenêtre
        stage.setOnCloseRequest(we -> {
            ViewController vc = ViewController.getInstance();
            vc.delView(user);
        });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setTitle(user.getPseudo() + " - Conversation");
        pseudo_label.setText(user.getPseudo());
        status_label.setText("\"" + user.getStatus().toString() + "\"");


        //Envoi de message
        message_write.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                controller.sendMessage(user, message_write.getText());
                addMessage(true, message_write.getText());
                message_write.setText("");
                ke.consume();
            }
        });


    }


    public void addMessage(boolean me, String message) {
        Text caption = new Text();
        Text msg = new Text();
        Text bullet = new Text();
        bullet.setText("•  ");
        bullet.setStyle("-fx-fill: #828282;");
        caption.setStyle("-fx-fill: #828282;");
        if(me) {
            if(!was_me || messages_received.getChildren().isEmpty() || was_dialog) {
                caption.setText(System.lineSeparator() + UserList.getMoi().getPseudo() + " dit :" + System.lineSeparator());
                Platform.runLater(() -> messages_received.getChildren().add(caption));
                was_me = true;
            }
        } else {
            Platform.runLater(() -> last_message_date_label.setText("Dernier message reçu à " + heure_format.format(new Date()) + " le " + date_format.format(new Date())));
            if(was_me || was_dialog) {
                caption.setText(System.lineSeparator() + user.getPseudo() + " dit :" + System.lineSeparator());
                Platform.runLater(() -> messages_received.getChildren().add(caption));
                was_me = false;
            }
        }
        msg.setText(message + System.lineSeparator());
        Platform.runLater(() -> messages_received.getChildren().addAll(bullet, msg));

        //ce n'était pas un dialog ;)
        was_dialog = false;
    }


    public void refreshStatus(boolean me) {
        User u= (me ? UserList.getMoi() : user);
        if(!me) {
            Platform.runLater(() -> status_label.setText("\"" + u.getStatus().toString() + "\""));
        }
    }


    // TODO : À améliorer
    public void getOffline() {
        message_write.getStyleClass().add("message_write_not_editable");
        message_write.setEditable(false);
        offline = true;
    }




    @FXML
    public void handleFileButton() throws IOException {
        if(!offline) {
            System.out.println("FICHIERERERERER");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(stage);

            controller.sendFile(user, file);
        }
    }


}
