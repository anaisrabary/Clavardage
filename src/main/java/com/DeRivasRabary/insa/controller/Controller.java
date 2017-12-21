package com.DeRivasRabary.insa.controller;

import com.DeRivasRabary.insa.model.*;
import com.DeRivasRabary.insa.model.packet.Notification;
import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.ui.ChatSessionView;
import com.DeRivasRabary.insa.ui.ViewController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class Controller implements Observer {

    @Override
    public void update(Observable o) {
        newUserRoutine();
    }

    public enum App_State_t {
        CONNECTED,
        DISCONNECTED
    }

    //private NetworkInterfaceParrot ni;
    private ClavardageNI ni;
    private App_State_t state;

    public Controller() {
        state = App_State_t.DISCONNECTED;
        ni = ClavardageNI.getInstance();
    }

    public void connect(String pseudo) {
        state = App_State_t.CONNECTED;
        UserList.createMe(pseudo);
        ni.launchNetwork();
        ni.broadcastNotification(Notification.Notification_type.CONNECT);
    }

    public void sendMessage(User dest, String message) {
        System.out.println("Envoi d'un message");
        ni.transmitMessage(message, dest);
    }
    public void sendFile(User dest, File file) throws IOException {
        System.out.println("Envoi d'un fichier");
        ni.transmitFile(file, dest);
    }


    public void changeStatus(String status) {
        User_Status st = null;
        switch (status) {
            case "Disponible":
                st = User_Status.ONLINE;
                break;
            case "Occupé":
                st = User_Status.BUSY;
                break;
        }
        UserList.getMoi().setStatus(st);
        HashMap<String, ChatSessionView> map = ViewController.getInstance().getAllViews();
        for(Map.Entry<String, ChatSessionView> entry : map.entrySet()) {
            ChatSessionView view = entry.getValue();

            view.getChatWindowController().refreshStatus(true);
        }
        System.out.println("envoi du status change");
        ni.broadcastNotification(Notification.Notification_type.STATUS_CHANGE, status);
    }


    public void newUserRoutine() {
        changeStatus(UserList.getMoi().getStatus().toString());
    }

    public void disconnect() {
        ni.broadcastNotification(Notification.Notification_type.DISCONNECT);
    }

    public App_State_t getState() {
        return state;
    }


    public static byte[] readBytesFromFile(java.io.File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            throw new IOException("Could not completely read file " + file.getName() + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }


}
