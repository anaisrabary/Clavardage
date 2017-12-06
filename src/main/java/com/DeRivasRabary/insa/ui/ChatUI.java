package com.DeRivasRabary.insa.ui;

import com.DeRivasRabary.insa.factory.PacketFactory;
import com.DeRivasRabary.insa.network.ClavardageNI;
import com.DeRivasRabary.insa.network.packet.Message;
import com.DeRivasRabary.insa.ui.infrastructure.Terminal;


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

    public void principal(){
        boolean open = true;
        ActionChooser actionChooser = new ActionChooser(terminal,this);
        do {
            actionChooser.askActionOn(this);

        }while(open);
    }

    /**
     * Demande à l'utilisateur à quel IP et port envoyer un message
     * TODO : Gérer les pseudo !!
     */
    public void onSend() {
        try {
            System.out.print("Destination IP address : ");
            String ipAddressDest = terminal.readLine();
            String ipAdressSource = getLocalAdress();
            System.out.print("Message : ");
            String message = terminal.readLine();

            Message monPacketEnvoyer = packetFactory.createPacketMessage(ipAdressSource, ipAddressDest, "totoPseudo", message);

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
