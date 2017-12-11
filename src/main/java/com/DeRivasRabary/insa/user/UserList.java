package com.DeRivasRabary.insa.user;


import com.DeRivasRabary.insa.exception.BeMoreSpecificWithThePeudo;
import com.DeRivasRabary.insa.exception.UtilisateurNonTrouve;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

// TODO : Si on veut être cohérent il faut que User List soit un HASMAP !!!! :) Refactor lourd et long à faire
public class UserList {


    public ArrayList<User> userList ;


    public static UserList instance ;


    public static UserList createInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }



    /** Constructeur ()
     * */
    public UserList(){
        this.userList = new ArrayList<User>();
    }


    public ArrayList<User> getUserList() { return  userList ;}



    public void addUser(User user){
        this.userList.add(user);
    }

    public void addUserList(ArrayList<User> userList){
        ListIterator<User> it = userList.listIterator();
        while(it.hasNext()){
            this.userList.add(it.next());
        }
    }




    public static UserList getInstance() {
        return instance;
    }

    /**
     * Méthode qui renvoie si il existe, l'utilisateur connaissant son Ip
     * @param ip
     * @return User
     * @throws UtilisateurNonTrouve
     */
    public User findUserByIp(String ip)throws UtilisateurNonTrouve {
        ListIterator<User> it = userList.listIterator();
        User current ;
        User foundUser = new User("","") ;
        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.getIPAdress()== ip){
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
     * Récupère l'user connaissant le pseudo d'un utilisateur exact
     * @param pseudo
     * @return User
     * @throws UtilisateurNonTrouve
     */
    public User findUserByPseudoExact(String pseudo) throws UtilisateurNonTrouve, BeMoreSpecificWithThePeudo {

        ListIterator<User> it = userList.listIterator();
        User current ;
        User foundUser = new User("","") ;


        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.getPseudo() == pseudo){
                trouve = true ;
                foundUser=current;
            }
        }
        if (!trouve){

        }
        return foundUser;
    }

    /**
     * Récupère l'user connaissant le pseudo d'un utilisateur sans qu'il soit exact
     * @param partPseudo
     * @return ArrayList<User>
     * @throws UtilisateurNonTrouve
     */
    public ArrayList<User> findListUserByPseudo(String partPseudo) throws UtilisateurNonTrouve {

        ListIterator<User> it = userList.listIterator();
        User current ;
        ArrayList<User> list = new ArrayList<>();

        while(it.hasNext()) {
            current = it.next();
            if (current.getPseudo().contains(partPseudo)){
                list.add(current);
            }
        }
        return list;
    }



    /**
     * Met à jour le pseudo connaissant l'IP d'un utilisateur
     * @param ip
     * @param newPseudo
     * @throws UtilisateurNonTrouve
     */
    public void updatePseudobyIp(String ip,String newPseudo) throws UtilisateurNonTrouve {
        ListIterator<User> it = userList.listIterator();
        User current ;
        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.getIPAdress() == ip){
                trouve = true ;
                current.changePseudo(newPseudo);
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur avec cet IP");
        }
    }


    /**
     * Methode qui affiche la liste des utilisateurs
     * @return
     */
    @Override
    public String toString(){
        String message = "" ;
        for (User u: this.userList) {
            message = message + u.toString();
        }
        return message ;
    }

}


        /* TODO : A supprimer à priroi
         autre alternative qui ne marche pas ... mais il faudrait pouvoir trouver quelqu'un ne connaissant qu'une partie de son pseudo..
        List<User> matchingUsers = userList.stream()
                .filter(user -> user.pseudoMatches(pseudo))
                .collect(Collectors.toList());
        if (matchingUsers.size() > 0) {
            matchingUsers.forEach(this::print);
        } else {
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant ce pseudo : " + pseudo);
        }
        if (matchingUsers.size() == 1)
            return matchingUsers.get(0);
        else
            throw new BeMoreSpecificWithThePeudo("soyez plus précis dans votre recherche");
        */
