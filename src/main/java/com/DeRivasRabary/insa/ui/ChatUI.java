package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.factory.PacketFactory;
import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.network.packet.Message;
import com.DeRivasRabary.insa.ui.infrastructure.Terminal;
import com.DeRivasRabary.insa.user.User;
import com.DeRivasRabary.insa.user.UserList;


import static com.DeRivasRabary.insa.network.ClasseTestReseau.getLocalAdress;

public class ChatUI{
    private static final String ERROR_MESSAGE = "[ERROR] An error occured while trying to listen on port";
    private static final String NOTIFICATION_FORMAT = "[INFO] Your message has been sent to %s on port %d";


    private final Terminal terminal;
    private final ClavardageNI clavardageNI;
    private final PacketFactory packetFactory ;


    public ChatUI(Terminal terminal, ClavardageNI clavardageNI) {
        this.terminal = terminal;
        this.clavardageNI = clavardageNI;
        this.packetFactory = new PacketFactory();
    }

    /**
     * Lorsque l'on veut ouvrit une session (ie on est déjà connecté-actif- on cherche le destinataire
     */
    public void oppeningSession(){

        // TODO : pour vraiment ouvrir une connection avec quelqu'un: l'emetteur doit faire un Hello au recepteur et le récepteur doit écouter.
        boolean open = true;
        try {
            terminal.print("Find your clavardage friend. \n Enter his/her/its pseudo :");
            User user =  clavardageNI.userList.findUserByPseudo(terminal.readLine()); //TODO : check why l'exception n'est pas levée si on trouve pas l'utilisateur...
            terminal.print( "HEY ! You're gonna clavard with : \n"+ user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }


        ActionChooser actionChooser = new ActionChooser(terminal,this);
        do {
            actionChooser.askActionOn(this);

        }while(open);
    }

    public void closingSession(){

    }

    /**
     * Demande à l'utilisateur à quel IP et port envoyer un message
     * TODO : Gérer les pseudo !!
     */
    public void onSend() {
        try {
            terminal.print("Destination IP address : "); // TODO : récuperer l'info du User trouvé
            String ipAddressDest = terminal.readLine();
            terminal.print("Message : ");
            String message = terminal.readLine();

            Message monPacketEnvoyer = packetFactory.createPacketMessage(getLocalAdress(), ipAddressDest, "totoPseudo", message);

            clavardageNI.onSend(monPacketEnvoyer);
            terminal.print(NOTIFICATION_FORMAT);
            terminal.print(monPacketEnvoyer.toString());
        }catch (Exception e){
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(e);
        }
    }

    public void onReceive () {
        try {
           clavardageNI.onReceive();
        } catch (Exception exception) {
            terminal.printError(ERROR_MESSAGE);
            terminal.printError(exception);
        }
    }

}
