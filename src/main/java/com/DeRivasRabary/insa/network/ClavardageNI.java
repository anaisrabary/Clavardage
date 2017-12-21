package com.DeRivasRabary.insa.network;


import com.DeRivasRabary.insa.controller.Controller;
import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import com.DeRivasRabary.insa.model.packet.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;

import static com.DeRivasRabary.insa.controller.Controller.readBytesFromFile;


public class ClavardageNI {
    private static final int NOTIFPORT = 1234;
    private static final int BDCASTPORT = 12345;
    public static int BASEPORT =12343;

    private static ClavardageNI instance ;

    private Controller controller;


    private DatagramSocket hello;
    private DatagramSocket notif; //socket de rencontre

    /**
     * Hello : pour connection avec quelqu'un donc (anouk)
     * Notif pour notification de changement (hello)
     */

    //Listener pour connexion
    private HelloListener helloListener;
    //Listener pour le notif
    private BroadcastListener notifListener;

    //Hasmap entre pseudo du contact er socket
    private HashMap<String, Integer> socketMap;

    //Table des sockets temporaires (utilisés pour le transfert de fichier par exemple)
    private HashMap<String, Integer> tmpSocketMap;

    //pour savoir quels sont les listeners actifs
    private HashMap<Integer, ChatListener> listenersMap;

    public static ClavardageNI createInstance(){
        if (instance == null) {
            instance = new ClavardageNI();
        }
        return instance;
    }

    public static ClavardageNI getInstance() {
        return instance;
    }


    public ClavardageNI(){
        //Initialisation des tables vides
        socketMap = new HashMap<>();
        tmpSocketMap = new HashMap<>();
        listenersMap = new HashMap<>();
            try {
                // le socket notif  en écoute :
                notif = new DatagramSocket(NOTIFPORT);
                notifListener = new BroadcastListener(notif);

                hello = new DatagramSocket(BDCASTPORT);
                helloListener = new HelloListener(hello);

            }catch (Exception e){
                e.printStackTrace();
            }


    }


    private synchronized Integer negotiatePort(User dest, boolean tmp) {
        System.out.println("Je vais négocier le port (temporaire -> " + tmp + ")");
        try {
            //System.out.println("anouk ok");
            ServerSocket communic = new ServerSocket(BASEPORT);
            //System.out.println("socket com ok");
            ChatListener listener = new ChatListener(communic);
            listener.start();
            listenersMap.put(BASEPORT, listener);
            //System.out.println("listener com ok");
            if(tmp) {
                sendControl(UserList.getMoi(), dest, Hello.Control_type.TMP_SOCKET, BASEPORT);
            } else {
                sendControl(UserList.getMoi(), dest, Hello.Control_type.HELLO, BASEPORT);
            }
            BASEPORT++;
            wait(); // Attends que le thread qui écoute les messages de contrôle mette à jour la liste de sockets
            //System.out.println("ok up to date");
            //System.out.println("full pseudo de recherche : " + dest.getFullPseudo());
            return (tmp ? tmpSocketMap.get(dest.getPseudo()) : socketMap.get(dest.getPseudo()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Socket getSocket(User dest, boolean tmp) {
        Integer port = (tmp ? tmpSocketMap.get(dest.getPseudo()) : socketMap.get(dest.getPseudo()));
        if(port == null) {
            port = negotiatePort(dest, tmp);
        } /*else {
            System.out.println("pas besoin de négocier, déjà en mémoire"); //FIXME else pour test uniquement
        }*/
        //System.out.println("Port négocié, tvb");
        try {
            return new Socket(dest.getIPAdress(), port.intValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void launchNetwork() {
        System.out.println("launch listener");
        notifListener.start();
        helloListener.start();
        helloListener.addObserver(controller);
    }
    //surcharge si pas de data
    public void sendNotification( User dest, Notification.Notification_type type) {
        sendNotification(dest, type, "");
    }

    public void transmit(PacketManager paquet, Socket s){
        try {
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            os.writeObject(paquet);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void sendNotification( User dest, Notification.Notification_type type, String data) {

        Socket s = getSocket(dest, false);
        System.out.println("Okay j'ai la socket pour balancer la sauce");
        Notification notification = new Notification(UserList.getMoi().getIPAdress(), dest.getIPAdress(), dest.getPseudo(), type, data);
        transmit(notification, s);

    }

    //surcharge s'il n'y a pas de data
    public void broadcastNotification(Notification.Notification_type type) {
        broadcastNotification(type, "");
    }

    public void broadcastNotification(Notification.Notification_type type, String data) {
        System.out.println("Broadcast to send");
        System.out.println("@ de broadcast : " + NetworkResourcefull.getBroadcastAddress().toString());
        Notification notification = new Notification( UserList.getMoi().getIPAdress(), NetworkResourcefull.getBroadcastAddress(),"bcast", type, data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(outputStream);
            os.writeObject(notification);
            byte[] buffer = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, NetworkResourcefull.getBroadcastAddress(), BDCASTPORT);
            notif.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void transmitMessage(String message, User dest) {
        Socket s = getSocket(dest, false);
        System.out.println("prêt à lancer le message");
        Message textMessage = new Message(UserList.getMoi().getIPAdress(), dest.getIPAdress(), dest.getPseudo(), message);
        transmit(textMessage, s);
    }

    public void transmitFile(java.io.File file, User dest) throws IOException {
        Socket s = getSocket(dest, false);
        System.out.println("Plus qu'à s'occuper du fichier !!");
        byte [] content = readBytesFromFile(file);

        File filemessage = new File( UserList.getMoi().getIPAdress(), dest.getIPAdress(), dest.getPseudo(),  file.getName(), URLConnection.guessContentTypeFromName(file.getName()), file.length(), content);
        transmit(filemessage, s);
    }

    protected void sendControl(User me, User dest, Hello.Control_type type, int data) {
        try {
            Hello control_packet = new Hello( me.getIPAdress(), dest.getIPAdress(), dest.getPseudo(), type, data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(control_packet);
            byte[] buffer = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, dest.getIPAdress(), NOTIFPORT);
            notif.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMap(String fullPseudo, int port) {
        socketMap.put(fullPseudo, port);
    }

    public void delMap(String fullPseudo) {
        socketMap.remove(fullPseudo);
    }

    public void addTmpMap(String fullPseudo, int port) {
        tmpSocketMap.put(fullPseudo, port);
    }

    public void delTmpMap(String fullPseudo) {
        tmpSocketMap.remove(fullPseudo);
    }

    public void addListener(int port, ChatListener listener) {
        listenersMap.put(port, listener);
    }

    public ChatListener getListener(int port) {
        return listenersMap.get(port);
    }

    public void delListener(ChatListener listener) {
        listenersMap.remove(listener);
        listener.interrupt();
    }

    public synchronized void fireUpdate() {
        notify();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}





