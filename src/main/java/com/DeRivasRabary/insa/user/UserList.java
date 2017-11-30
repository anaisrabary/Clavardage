package com.DeRivasRabary.insa.user;

import java.util.ArrayList;
import java.util.ListIterator;

public class UserList {

    public ArrayList<User> userList ;



    /** Constructeur qui rajoute qu'un utilisateur à la liste
     *
     * */
    public UserList(User user){
        userList.add(user);
    }

    /** Constructeur qui rajoute plusieurs utilisateurs à la liste
     * */
    public UserList(ArrayList<User> listeUser){
        this.userList=listeUser;
    }

    /**
     * Méthode qui renvoie si il existe, l'utilisateur connaissant son Ip
     * */
    public User getUserByIp(String ip)throws UtilisateurNonTrouve {
        ListIterator<User> it = userList.listIterator();
        User current ;
        User foundUser = new User("","") ;
        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.ip == ip){
                foundUser=current;
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant ce nom");
        }
        return foundUser;
    }

}
