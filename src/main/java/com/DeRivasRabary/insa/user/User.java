package com.DeRivasRabary.insa.user;


public class User  {

    private String pseudo ;
    private String ip ;


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
    public String getIPAdress (){ return ip;}

    /**
     * methode pour récupérer le pseudo
     * @return String
     */
    public String getPseudo () { return pseudo ;}

    /**
     * Méthode pour changer le pseudo
     * @param newpseudo
     */
    public void changePseudo(String newpseudo){
        this.pseudo = newpseudo ;
    }

}
