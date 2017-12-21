package com.DeRivasRabary.insa.network;

import com.DeRivasRabary.insa.model.UserList;
import com.DeRivasRabary.insa.model.packet.File;
import com.DeRivasRabary.insa.model.packet.Message;
import com.DeRivasRabary.insa.model.packet.PacketManager;
import com.DeRivasRabary.insa.ui.ChatSessionView;
import com.DeRivasRabary.insa.ui.ViewController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class ChatListener extends TCPListener {

    public ChatListener(ServerSocket server) {
        super(server);
    }


    @Override
    protected void managePacket(PacketManager p) {
        UserList users = UserList.getInstance();
        ClavardageNI ni = ClavardageNI.getInstance();
        //Réception de message

        //On regarde si c'est du texte ou un fichier
        if(p instanceof Message) {
            ViewController viewController = ViewController.getInstance();
            Message message = (Message) p;
            ChatSessionView view = viewController.getView(users.findUserByIp(message.getIpSender()), false);
            viewController.updateView(view, ViewController.Update_type.NEW_MESSAGE, message.getData());
        }

        if(p instanceof File) {
            File file = (File) p;

            try (FileOutputStream fileOuputStream = new FileOutputStream(file.getFileName())) {
                fileOuputStream.write(file.getContent());
                System.out.println(file.getFileName() + " a bien été reçu !!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

