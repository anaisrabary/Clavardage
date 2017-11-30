package com.DeRivasRabary.insa.user;


import com.DeRivasRabary.insa.exception.UtilisateurNonTrouve;
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
                trouve = true ;
                foundUser=current;
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant ce nom");
        }
        return foundUser;
    }

    /**
     * Récupère l'IP connaissant le pseudo d'un utilisateur
     * @param pseudo
     */
    public void getIpBypseudo(String pseudo){
        // transformer le retour void en User
        System.err.println("Pas implémenté !!");
    }

    /**
     * Met à jour le pseudo connaissant l'IP d'un utilisateur
     * @param ip
     */
    public void updatePseudobyIp(String ip){
        System.err.println("Pas implémenté !!");
    }



}
