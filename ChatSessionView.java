package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatSessionView {


    private Stage stage;
    private Controller controller;
    private User user;
    private ChatSessionViewController chatSessionViewController;

    public ChatSessionView(Controller ctrl,User user) throws IOException {
        this.stage = new Stage();
        this.controller = ctrl;
        this.user = user;
        chatSessionViewController = new ChatSessionViewController(this.stage, this.controller, user);
        stage.setScene(new Scene(chatSessionViewController));
        stage.setMinWidth(630);
        stage.setMinHeight(470);
        stage.setWidth(640);
        stage.setHeight(480);
    }


    public void show() {
        this.stage.show();
    }

    public void requestFocus() {
        stage.requestFocus();
    }

    public ChatSessionViewController getChatWindowController() {
        return chatSessionViewController;
    }

}
