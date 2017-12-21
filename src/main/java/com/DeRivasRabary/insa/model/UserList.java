package com.DeRivasRabary.insa.model;

import com.DeRivasRabary.insa.network.NetworkResourcefull;
import com.DeRivasRabary.insa.model.exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.InetAddress;
import java.util.*;
import java.util.function.Consumer;

public class UserList {

    private ObservableList<User> userlist ;
    private Consumer<User> addCallback = user -> {};
    private Consumer<User> delCallback = user -> {};
    private static User moi;

    public static UserList instance ;


    public static UserList createInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public static UserList getInstance() {
        return instance;
    }


    public UserList(){
        this.userlist =FXCollections.observableArrayList(User.extractor());
    }

    public void setAddCallback(Consumer<User> addCallback) {
        this.addCallback = addCallback ;
    }

    public void setDelCallback(Consumer<User> delCallback) {
        this.delCallback = delCallback ;
    }

    public void addUser(User u){
        if (!userExist(u)){
            System.out.println("ajout d'un utilisateur"+ u.toString());
            userlist.add(u);
        }
    }

    public boolean userExist(User u){
        for (User object : userlist){
            if (object.getIPAdress().equals(u.getIPAdress())){
                return true ;
            }
        }
        return false ;
    }

    public void removeUser(User u){
        this.userlist.remove(u);
    }

    public User findUserByIp(InetAddress ip) {
        for (User object : userlist){
            if (object.getIPAdress().equals(ip)){
                return object;
            }

        }
        return null;
    }


    /**
     * Récupère l'user connaissant le pseudo d'un utilisateur exact
     * @param pseudo
     * @return User
     * @throws UtilisateurNonTrouve
     */
    public User findUserByPseudoExact(String pseudo) throws UtilisateurNonTrouve{

        ListIterator<User> it = userlist.listIterator();
        User current ;


        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.getPseudo() == pseudo){
                trouve = true ;
                return current;
            }
        }
        if (!trouve) throw new UtilisateurNonTrouve(pseudo) ;
        return null ;
    }

    /**
     * Récupère l'user connaissant le pseudo d'un utilisateur sans qu'il soit exact
     * @param partPseudo
     * @return ArrayList<User>
     * @throws UtilisateurNonTrouve
     */
    public ArrayList<User> findListUserByPseudo(String partPseudo) throws UtilisateurNonTrouve{

        ListIterator<User> it = userlist.listIterator();
        User current ;
        ArrayList<User> list = new ArrayList<>();

        while(it.hasNext()) {
            current = it.next();
            if (current.getPseudo().contains(partPseudo)){
                list.add(current);
            }
        }
        if (list.size()==0) throw new UtilisateurNonTrouve(partPseudo) ;
        return list;
    }





    /**
     * Met à jour le pseudo connaissant l'IP d'un utilisateur
     * @param ip
     * @param newPseudo
     * @throws UtilisateurNonTrouve
     */
    public void updatePseudobyIp(InetAddress ip,String newPseudo) throws UtilisateurNonTrouve {
        ListIterator<User> it = userlist.listIterator();
        User current ;
        boolean trouve = false ;
        while(it.hasNext() & !trouve) {
            current = it.next();
            if (current.getIPAdress().equals(ip)){
                trouve = true ;
                current.changePseudo(newPseudo);
            }
        }
        if (!trouve){
            throw new UtilisateurNonTrouve("Pas d'utilisateur avec cet IP");
        }
    }


    public static void createMe (String pseudo) {
        moi = new User(pseudo, NetworkResourcefull.getLocalAdress());
    }

    public static User getMoi() { return moi;}


    /**
     * Methode qui affiche la liste des utilisateurs
     * @return
     */
    @Override
    public String toString(){
        String message = "" ;
        for (User u: this.userlist) {
            message = message + u.toString();
        }
        return message ;
    }

    public ObservableList<User> getUserlist() {
        return userlist;
    }
}
