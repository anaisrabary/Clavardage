package com.DeRivasRabary.insa.User;

import java.net.InetAddress;

public class User  {
    public String pseudo ;
    public String ip ;


    /* Constructeur test puisqu'on entre l'ip manuellement */
    public User (String pseudo, String ip){
        this.pseudo = pseudo ;
        this.ip = ip ;
    }

}
