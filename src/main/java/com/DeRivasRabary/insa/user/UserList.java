package com.DeRivasRabary.insa.user;


import com.DeRivasRabary.insa.exception.BeMoreSpecificWithThePeudo;
import com.DeRivasRabary.insa.exception.UtilisateurNonTrouve;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class UserList {

    public ArrayList<User> userList ;


    public static UserList instance ;


    public static UserList createInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }
    public UserList(){
        this.userList = new ArrayList<User>();
    }
    /** Constructeur qui rajoute qu'un utilisateur à la liste
     * TODO : Supprimez moi ce constructeur !!!
     * */
    public UserList(User user){
        userList = new ArrayList<User>();
        userList.add(user);
    }


    /** Constructeur qui rajoute plusieurs utilisateurs à la liste
     * TODO : celui là aussi ( à remplacer pourquoi pas par une méthode qui permet d'ajouter une liste de user #concaténation
     * */
    public UserList(ArrayList<User> listeUser){
        this.userList=listeUser;
    }

    /*
        Créer un constructeur qui peut prendre aussi bien 0 que n utilisateurs
     */


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
     * @return User
     * @throws UtilisateurNonTrouve
     */
    public User findUserByPseudo(String pseudo) throws UtilisateurNonTrouve, BeMoreSpecificWithThePeudo {

        /* TODO : autre alternative qui ne marche pas ... mais il faudrait pouvoir trouver quelqu'un ne connaissant qu'une partie de son pseudo..
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

        }
        return foundUser;

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
            if (current.ip == ip){
                trouve = true ;
                current.pseudo=newPseudo;
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur portant cet IP");
        }
    }


    /**
     * TODO : je ne comprends pas à quoi sert message...
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

    /**
     * Alternative pour faire un toString
     * @param user
     */
    private void print(User user) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        stringJoiner.add(user.toString());
        System.out.println(stringJoiner.toString());
    }
}
