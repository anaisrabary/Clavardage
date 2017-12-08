package com.DeRivasRabary.insa.network;


import com.DeRivasRabary.insa.network.packet.PacketManager;
import com.DeRivasRabary.insa.user.UserList;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class ClavardageNI implements IncomingMessageListener{
    private  static final int PORT = 1234;
    public final UserList userList ; // TODO : supprimer le public et mettre private !!


    public ClavardageNI(){
        this.userList = UserList.createInstance();
    }

    /**
     * Envoie un packet sur le réseau
     */
    public void onSend(PacketManager packet) {
       UDPMessageSenderManager udpMessageSenderManager = new UDPMessageSenderManager();
       try {
           if ( packet.isPacketMESSAGE())
            udpMessageSenderManager.sendMessageOn(packet);
           if (packet.isPacketBYE() || packet.isPacketHELLO()); // TODO !!!

       }catch (Exception e){
           e.printStackTrace();
       }

    }

    /**
     * Reçoit un message du réseau
     * @throws Exception
     */
    public void onReceive() throws Exception{
        try {
            UDPMessageReceiverManager udpMessageReceiverManager = new UDPMessageReceiverManager(PORT);
            udpMessageReceiverManager.listenOnPort(PORT,this ) ;
            udpMessageReceiverManager.close();

        }
        catch (Exception e){
            System.out.println("yolo");
            e.printStackTrace();
        }
    }

    /**
     * Affiche un message reçu
     *  @param packet
     */
    @Override
    public void onNewIncominMessage(String packet) {
       System.out.println("New incoming message: \n" + packet);
    }

    //TODO : Envoyer des hello réguliers sur le réseau pour signifier aux autres qu'on est là.
    /**
     * Tenir une table (de type Hashmap) qui fait la correspondance entre IP/pseudo/depuis combien de temps on n'a pas de nouvelle
     * En gros on met un compteur de 20mn, a chaque fois que l'on reçoit un message de lui on réinitialise le compteur
     * à 20mn.
     * Si on arrive à timeout au bout des 20mn, on envoie un hello specifique avec replyrec=true. Si pas de réponse
     * alors on le supprime et on considère qu'il n'est plus sur le réseau. (peut-il partager l'info avec tout le monde ? #PeerToPeer ?)
     * **/

    // TODO : par rapport à l'idée PeerToPeer, Si un utilisateur découvre une information avant les autres, il la partage.
    // dans le cas où il est parti sans dire au-revoir par exemple...
    /**
     * En gros je découvre qu'un utilisateur n'est plus actif, j'envoie un paquet d'information (comme un ICMP) à tout le monde
     * qui dit en gros "Machin n'est plus sur le réseau". A voir...
     */

    //TODO : verifier l'unicite du pseudo sur le réseau
    /**
     * Envoyer pseudo choisi sur le réseau et demander aux autres de nous reply si ils l'utilisent ou pas
     */

    //TODO : Récupérer la liste des utilisateurs sur le réseau et faire.
    // Methode "onConnect"


    // TODO : supprimer celle de test Reseaux
    public static String getLocalAdress() {
        try
        {
            boolean trouve = false;
            String ip = "" ;
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (!trouve || e.hasMoreElements()) {
                Enumeration<InetAddress> i = e.nextElement().getInetAddresses();
                while (i.hasMoreElements()) {
                    InetAddress a = i.nextElement();
                    if (a.isSiteLocalAddress()) {
                        trouve = true;
                        ip=a.getHostAddress();
                    }
                }
            }
            return ip ;
        } catch (Exception e){
            System.err.println("Erreur dans getLocalAdress. Impossible de trouver ip privée locale.\n" +
                    "Etes vous bien connecté au réseau ?");
            return "";
        }
    }


}
