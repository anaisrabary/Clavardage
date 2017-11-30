package com.DeRivasRabary.insa.User;

import java.net.InetAddress;

public class LocalUser extends User {
    public boolean actif ;



    public LocalUser(String pseudo,String ip, boolean actif){
        super(pseudo,ip);
        this.actif = actif ;
    }





}
