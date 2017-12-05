package com.DeRivasRabary.insa.user;


public class User  {

    public String pseudo ;
    public String ip ;


    /**
     * Constructeur de USER
     * TEMPORAIRE sans doute car on lui passe l'IP pour le moment !
     * @param pseudo
     * @param ip
     */
    public User (String pseudo, String ip){
        this.pseudo = pseudo ;
        this.ip = ip ;
    }

    @Override
    public String toString(){
        return "pseudo : " + this.pseudo + ", ip : " + this.ip + "\n" ;
    }

}
