package com.DeRivasRabary.insa.network;

import java.net.DatagramPacket;

public class ClavardageNI implements IncomingMessageListener{
    private  static final int PORT = 1234;
    public ClavardageNI(){
    }

    /**
     * Envoie un message sur le réseau
     * TODO : transformer message en paquet
     * @param ipAddress
     * @param port
     * @param message
     */
    public void onSend(String ipAddress, String port, String message ) {
       UDPMessageSenderManager udpMessageSenderManager = new UDPMessageSenderManager();
       try {
           udpMessageSenderManager.sendMessageOn(ipAddress, port, message);
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    /**
     * Reçoit un message du réseau
     * TODO : gérer la fermeture d'écoute car si on veut écouter plusieurs fois on a une exception. (méthode close que tu as commenté dans UDPMessage Receiver Manager)
     * @throws Exception
     */
    public void onReceive() throws Exception{
        try {
            System.out.println("here");
            UDPMessageReceiverManager udpMessageReceiverManager = new UDPMessageReceiverManager();
            udpMessageReceiverManager.listenOnPort(PORT,this ) ;

        }
        catch (Exception e){
            System.out.println("yolo");
            e.printStackTrace();
        }
    }

    /**
     * Affiche un message reçu
     * TODO : remplacer message par paquet et dans l'affichage tu peux juste faire paquet.toString(). J'ai déjà définit des méthodes et ça devrait marcher si tout va bien.
     * @param message
     */
    @Override
    public void onNewIncominMessage(String message) {
       System.out.println("New incoming message: " + message);
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
    /**
     * En gros je découvre qu'un utilisateur n'est plus actif, j'envoie un paquet d'information (comme un ICMP) à tout le monde
     * qui dit en gros "Machin n'est plus sur le réseau". A voir...
     */

    //TODO : verifier l'unicite du pseudo sur le réseau
    /**
     * Envoyer pseudo choisi sur le réseau et demander aux autres de nous reply si ils l'utilisent ou pas
     */

    //TODO : Récupérer la liste des utilisateurs sur le réseau et faire une Hashmap




}
