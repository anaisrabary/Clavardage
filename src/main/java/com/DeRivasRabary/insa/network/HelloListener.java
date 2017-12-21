package com.DeRivasRabary.insa.network;

import com.DeRivasRabary.insa.model.Observable;
import com.DeRivasRabary.insa.model.Observer;
import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import com.DeRivasRabary.insa.model.packet.Hello;
import com.DeRivasRabary.insa.model.packet.PacketManager;

import static com.DeRivasRabary.insa.network.ClavardageNI.BASEPORT;



import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Manage all control packets
 *
 * @author alex205
 */
public class HelloListener extends UDPListener implements Observable {


    private ArrayList<Observer> observers;

    public HelloListener(DatagramSocket socket) {
        super(socket);
        this.observers = new ArrayList<>();
    }

    @Override
    protected void managePacket(PacketManager p) {
        ClavardageNI ni = ClavardageNI.getInstance();
        UserList users = UserList.getInstance();
        if(p instanceof Hello) {
            Hello hey = (Hello) p;
            System.out.println("RECEIVED, le port :" + hey.getData());
            try {
                if(hey.getType() == Hello.Control_type.HELLO || hey.getType() == Hello.Control_type.ACK) {
                    //Gestion du socket de conversation
                    System.out.println("l'ip de " + hey.getPseudo() + " " + hey.getIpSender().toString());
                    ni.addMap(hey.getPseudo() + "@" + hey.getIpSender().toString(), hey.getData());
                    System.out.println("le pseudo dans la table " + hey.getPseudo() + "@" + hey.getIpSender().toString());
                    ni.fireUpdate();
                    if (hey.getType() == Hello.Control_type.HELLO) {
                        ServerSocket com = new ServerSocket(BASEPORT);
                        ChatListener listener = new ChatListener(com);
                        listener.start();
                        ni.addListener(BASEPORT, listener);
                        System.out.println("listener lancé");
                        ni.sendControl(UserList.getMoi(), new User(hey.getPseudo(), hey.getIpSender()), Hello.Control_type.ACK, BASEPORT);
                        BASEPORT++;
                        notifyObservers();
                    }
                } else if(hey.getType() == Hello.Control_type.TMP_SOCKET || hey.getType() == Hello.Control_type.TMP_SOCKET_ACK) {
                    //Gestion de la demande de socket temporaire
                    System.out.println("on demande un socket temporaire");
                    ni.addTmpMap(hey.getPseudo() + "@" + hey.getIpSender().toString(), hey.getData());
                    ni.fireUpdate();
                    System.out.println("c'est à jour");
                    if(hey.getType() == Hello.Control_type.TMP_SOCKET) {
                        ServerSocket com = new ServerSocket(BASEPORT);
                        ChatListener listener = new ChatListener(com);
                        listener.start();
                        ni.addListener(BASEPORT, listener);
                        ni.sendControl(UserList.getMoi(), users.findUserByIp(hey.getIpSender()), Hello.Control_type.TMP_SOCKET_ACK, BASEPORT);
                        BASEPORT++;
                    }
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(int i=0;i<observers.size();i++)
        {
            Observer o = (Observer) observers.get(i);
            o.update(this);// On utilise la méthode "tiré".
        }
    }
}
