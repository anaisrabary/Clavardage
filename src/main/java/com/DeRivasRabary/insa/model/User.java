package com.DeRivasRabary.insa.model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;

import javafx.util.Callback;
import java.net.InetAddress;

/**
 * Represente un utilisateur dans le chat system
 */
public class User  {

    private String pseudo ;
    private InetAddress ip;
    private SimpleObjectProperty status ;




    /**
     * Constructeur de USER
     * TEMPORAIRE sans doute car on lui passe l'IP pour le moment !
     * @param pseudo
     * @param ip
     */
    public User (String pseudo, InetAddress ip){
        this.pseudo = pseudo ;
        this.ip = ip ;
        this.status = new SimpleObjectProperty(User_Status.ONLINE);
    }

    @Override
    public String toString(){
        return "pseudo : " + this.pseudo + ", ip : " + this.ip + "\n" ;
    }

    /**
     * Pour savoir si le pseudo correspond
     * @param pattern
     * @return
     */
   // public boolean pseudoMatches(String pattern) { return this.pseudo.toLowerCase().contains(pattern);}

    /**
     * Methode pour renvoyer l'@IP d'un utilisateur
     * @return String
     */
    public InetAddress getIPAdress (){ return ip;}

    /**
     * methode pour récupérer le pseudo
     * @return String
     */
    public String getPseudo () { return pseudo ;}

    /**
     * méthode pour récupérer le statut
     * @return
     */
    public Object getStatus() {
        return status.get();
    }

    /**
     * Méthode pour changer le pseudo
     * @param newpseudo
     */
    public void changePseudo(String newpseudo){
        this.pseudo = newpseudo ;
    }

    public void setStatus (User_Status status){
        this.status.set(status);
    }
    public InetAddress hashFunction(){
        return getIPAdress();
    }
    /**
     * Callback pour mettre à jour la Map observable dans la vue.
     * Sets the parameters to be observed
     * @return Observable[]
     */
    public static Callback<User, Observable[]> extractor() {
        return param -> new Observable[]{param.status};
    }



}
