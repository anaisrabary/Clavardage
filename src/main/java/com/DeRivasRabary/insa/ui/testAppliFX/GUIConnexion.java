package com.DeRivasRabary.insa.ui.testAppliFX;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GUIConnexion {

    public Button buttonConnexion;
    public TextField champPseudo;
    public boolean pseudoOK = false;
    private static GUIConnexion instance;
    public String pseudo = "null";

    public GUIConnexion() { instance=this; }

    public static GUIConnexion getInstance() {
        return instance;
    }

    public void onOKButtonClicked(ActionEvent actionEvent) {
        String pseudoPropose = champPseudo.getCharacters().toString();
        //if ((!pseudoPropose.isEmpty()) && (pseudoPropose.length()<30) && (!UserList.getInstance().findUserByPseudo(pseudoPropose)) ) {
            pseudoOK=true;
            pseudo = pseudoPropose ;
            champPseudo.setStyle("-fx-background-color: #ffffff;");

        //} else {
            champPseudo.setStyle("-fx-background-color: #FE5151;");
            champPseudo.clear();
        //}

    }

    public boolean pseudoOK() { return pseudoOK; }


}