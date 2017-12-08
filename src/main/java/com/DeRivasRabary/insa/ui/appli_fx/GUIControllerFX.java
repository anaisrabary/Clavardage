package com.DeRivasRabary.insa.ui.appli_fx;

        import java.util.ListIterator;


        import com.DeRivasRabary.insa.factory.PacketFactory;
        import com.DeRivasRabary.insa.network.ClavardageNI;
        import com.DeRivasRabary.insa.network.packet.Message;
        import com.DeRivasRabary.insa.user.User;
        import com.DeRivasRabary.insa.user.UserList;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;
        import javafx.scene.text.Text;
        import javafx.scene.text.TextAlignment;
        import javafx.scene.text.TextFlow;

        import static com.DeRivasRabary.insa.network.ClasseTestReseau.getLocalAdress;

public class GUIControllerFX {


    public Label labelPseudoUtilisateur = new Label();
    public TextFlow openedChatSession = new TextFlow();
    public TextArea textInput = new TextArea();
    public Button sendButton = new Button();

    private static GUIControllerFX instance;
    public TextFlow users;
    public Text nomUserCourant;
    public User userCourant;
    public User userPrécédent; // TODO : ???
    private final PacketFactory packetFactory ;
    private UserList userList ;

    public GUIControllerFX() {
        instance=this;
        userPrécédent=null;
        packetFactory = new PacketFactory();
        userList = new UserList();
    }



    public static GUIControllerFX getInstance() { return instance; }

    public void setPseudoUser (String pseudo){
        if (pseudo != null)
            labelPseudoUtilisateur.setText(pseudo);
        else
            labelPseudoUtilisateur.setText("Aucun nom trouve pour l'utilisateur");
    }

    public void onSendButtonClicked (){


        //Conversion de l'input en String
        String message = "";

        for (CharSequence cs : textInput.getParagraphs()) {
            message=message+cs.toString()+"\n";
        }

        if (userCourant!=null) {
            //Si l'utilisateur a changé on nettoie la fenêtre
            if ( !(userCourant.equals(userPrécédent)))
                openedChatSession.getChildren().clear();
            userPrécédent=userCourant;

            Message monPacketEnvoyer = packetFactory.createPacketMessage(getLocalAdress(), userCourant.getIPAdress(), "totoPseudo", message);

            ClavardageNI.getInstance().onSend(monPacketEnvoyer, userCourant.getIPAdress());
            message = "Moi :\n" + monPacketEnvoyer.toString();
            Text text = new Text(message);
            textInput.clear();
            openedChatSession.getChildren().addAll(text);
        } else {
            textInput.clear();
            openedChatSession.getChildren().clear();
            Text text = new Text("ERREUR : Aucun interlocuteur de sélectionné.");
            openedChatSession.getChildren().addAll(text);
        }






    }


    public void onNewIncomingMessage(String message){
        afficherConversation(userCourant);
        String messageAEnvoyer ="Reçu :\n"+message;
        Text text = new Text(messageAEnvoyer);
        openedChatSession.getChildren().addAll(text);

    }

    public void afficherConversation(User userDest){
        userCourant = userDest;
        nomUserCourant.setText(userCourant.getPseudo());
    }

    public void updateContacts() {
        users.getChildren().clear();
        ListIterator<User> it = userList.getInstance().getUserList().listIterator();
        while (it.hasNext()){
        //for() {
            // Map.Entry<InetAddress, User> entry : Messagerie.getInstance().mapUsersByIP.entrySet()) {
            User user = it.next();
            Text text = new Text(user.getPseudo()+"  ");
            users.getChildren().addAll(text);
            Button btn = new Button("Parler");
            btn.setTextAlignment(TextAlignment.RIGHT);
            btn.setOnAction( (javafx.event.ActionEvent e) -> afficherConversation(user));
            btn.setStyle("float: right;");
            users.getChildren().addAll(btn);
            Text retourCharriot = new Text("\n");
            users.getChildren().addAll(retourCharriot);
        }

    }
}