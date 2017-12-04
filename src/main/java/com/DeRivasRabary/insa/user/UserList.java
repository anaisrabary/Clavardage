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

    /*
        Créer un constructeur qui peut prendre aussi bien 0 que n utilisateurs
     */


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
     * Récupère l'user connaissant le pseudo d'un utilisateur
     * @param pseudo
     */
    public User getUserByPseudo(String pseudo) throws UtilisateurNonTrouve {
        ListIterator<User> it = userList.listIterator();
        User current ;
        User foundUser = new User("","") ;


        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.pseudo == pseudo){
                trouve = true ;
                foundUser=current;
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant ce pseudo");
        }
        return foundUser;
    }



    /**
     * Met à jour le pseudo connaissant l'IP d'un utilisateur
     * @param ip
     */
    public void updatePseudobyIp(String ip,String newPseudo) throws UtilisateurNonTrouve {
        ListIterator<User> it = userList.listIterator();
        User current ;
        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.ip == ip){
                trouve = true ;
                current.pseudo=newPseudo;
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant cet IP");
        }
    }



    @Override
    public String toString(){
        String message = "" ;
        for (User u: this.userList) {
            message = message + u.toString();
        }
        return message ;
    }
}
