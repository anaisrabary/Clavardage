package com.DeRivasRabary.insa.user;



public class LocalUser extends User {

    public boolean actif ;

    /**
     * Constructeur d'un local User
     * TEMPORAIRE car l'IP va disparaitre
     * @param pseudo
     * @param ip
     * @param actif
     */
    public LocalUser(String pseudo,String ip, boolean actif){
        super(pseudo,ip);
        this.actif = actif ;
    }

    public void changeEtatActif(boolean actif){
        this.actif = actif ;
    }


}
