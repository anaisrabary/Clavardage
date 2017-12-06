package com.DeRivasRabary.insa.network;


import com.DeRivasRabary.insa.network.packet.PacketManager;


public class ClavardageNI implements IncomingMessageListener{
    private  static final int PORT = 1234;
    public ClavardageNI(){
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
     * TODO : gérer la fermeture d'écoute car si on veut écouter plusieurs fois on a une exception. (méthode close que tu as commenté dans UDPMessage Receiver Manager)
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
     * TODO : remplacer message par paquet et dans l'affichage tu peux juste faire paquet.toString().
     * TODO : c'est pas si simple que ça.. je ne sais pas comment dire que ce que je reçois d'un UDP (des byte) c'est un packet et pas un String....
     * TODO : ça s'affiche comme on veut en tout cas
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
