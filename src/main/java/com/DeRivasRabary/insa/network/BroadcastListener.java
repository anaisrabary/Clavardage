package com.DeRivasRabary.insa.network;

import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import com.DeRivasRabary.insa.model.User_Status;
import com.DeRivasRabary.insa.model.packet.Notification;
import com.DeRivasRabary.insa.model.packet.PacketManager;
import com.DeRivasRabary.insa.ui.ViewController;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Manage all broadcast packets
 *
 */
public class BroadcastListener extends UDPListener{

    public BroadcastListener(DatagramSocket socket) {
        super(socket);
    }

    @Override
    protected void managePacket(PacketManager p) throws IOException {
        ClavardageNI ni = ClavardageNI.getInstance();
        ViewController viewController = ViewController.getInstance();
        if(p instanceof Notification) {
            System.out.println("traitement notification");
            Notification n = (Notification) p;
                UserList users = UserList.getInstance();
                if(!n.getIpSender().equals(UserList.getMoi().getIPAdress())) {
                    /*System.out.println("IP sender : " + n.getIpSender().toString());
                    System.out.println("IP receiver : " + n.getIpReceiver().toString());*/
                    System.out.println(n.toString());
                    switch (n.getTypeN()) {
                        case CONNECT:
                            System.out.println(n.getPseudoEmmeteur() + " vient de se connecter");
                            User u = new User(n.getPseudoEmmeteur(), n.getIpSender());
                            if(!users.userExist(u)) {
                                System.out.println("contact ajouté dans la table");
                                users.addUser(u);
                            }
                            System.out.println("on répond de notre présence à " + u.getPseudo());
                            ni.sendNotification(u, Notification.Notification_type.ACK_CONNECT);
                            break;
                        case DISCONNECT:
                            System.out.println(n.getPseudoEmmeteur() + " vient de se déconnecter");
                            User u1 = users.findUserByIp (n.getIpSender());
                            viewController.updateView(viewController.getView(u1, false), ViewController.Update_type.NOT_EDITABLE, "");
                            users.removeUser(u1);
                            ni.delMap( n.getIpSender().toString());
                            break;
                        case STATUS_CHANGE:
                            System.out.println(n.getPseudoEmmeteur() + " est maintenant " + n.getData());
                            User_Status status = User_Status.ONLINE; //valeur par défaut au cas où
                            //un peu sale mais efficace ! (pour envoyer l'enum dans le paquet en string)
                            switch (n.getData()) {
                                case "Disponible":
                                    status = User_Status.ONLINE;
                                    break;

                                case "Occupé":
                                    status = User_Status.BUSY;
                                    break;
                            }
                            User contact = users.findUserByIp(n.getIpSender());
                            /*contact.setStatus(status); // changement du statut dans le contact*/
                            //changement du statut dans la vue si elle est ouverte
                            if(viewController.viewExists(contact)) {
                                viewController.updateView(viewController.getView(contact, false), ViewController.Update_type.STATUS_CHANGE, "");
                            }
                            break;
                        default:
                            System.out.println("Can't read this notification");
                    }
                }
        } else {
            throw new IOException("Unknown packet");
        }
    }
}
