package com.DeRivasRabary.insa.user;

import com.DeRivasRabary.insa.exception.BeMoreSpecificWithThePeudo;
import com.DeRivasRabary.insa.exception.UtilisateurNonTrouve;

import java.util.*;
import java.util.Map.*;

public class UserListMap {

    private Map<String,User> hashmap ;

    public static UserListMap instance ;


    public static UserListMap createInstance() {
        if (instance == null) {
            instance = new UserListMap();
        }
        return instance;
    }

    public static UserListMap getInstance() {
        return instance;
    }


    public UserListMap(){
        this.hashmap = new HashMap<>();
    }

    public void addUser(User u){
        this.hashmap.put(u.hashFunction(),u);
    }

    public void addUserList(HashMap<String,User> hm){
        this.hashmap.putAll(hm);
    }




    public void removeUser(User u){
        String hash = u.hashFunction();
        this.hashmap.remove(hash);
    }

    public User findUserByIp(String ip)throws UtilisateurNonTrouve {
        Set<Entry<String, User>> setHm = hashmap.entrySet();
        Iterator<Entry<String, User>> it = setHm.iterator();
        Boolean trouve = false;
        User u = new User("", "");
        while (it.hasNext() & !trouve) {
            Entry<String, User> e = it.next();
            if (e.getValue().getIPAdress() == ip) {
                trouve = true;
                u = e.getValue();
            }
        }
        if (trouve) {
            return u;
        } else {
            throw new UtilisateurNonTrouve("Utilisateur non trouve");
        }

    }


    public ArrayList<User> findListUserByPseudo(String partPseudo) throws UtilisateurNonTrouve {
        Set<Entry<String, User>> setHm = hashmap.entrySet();
        Iterator<Entry<String, User>> it = setHm.iterator();
        ArrayList<User> listUser = new ArrayList<>();
        while (it.hasNext()) {
            Entry<String, User> e = it.next();
            if (e.getValue().getPseudo().contains(partPseudo)) {
                listUser.add(e.getValue());
            }
        }
        return listUser;
    }


    public void updatePseudobyIp(String ip,String newPseudo) throws UtilisateurNonTrouve {
        Set<Entry<String, User>> setHm = hashmap.entrySet();
        Iterator<Entry<String, User>> it = setHm.iterator();
        Boolean trouve = false ;
        while (it.hasNext() & !trouve) {
            Entry<String, User> e = it.next();
            if (e.getValue().getIPAdress()==ip) {
                trouve = true ;
                User u = e.getValue();
                u.changePseudo(newPseudo);
                e.setValue(u);
            }
        }
    }

    public String toString(){
        Set<Entry<String, User>> setHm = hashmap.entrySet();
        Iterator<Entry<String, User>> it = setHm.iterator();
        String zeToString="" ;
        while (it.hasNext()) {
            Entry<String, User> e = it.next();
            zeToString = zeToString + e.getValue().toString();
        }
        return zeToString ;
    }

}
