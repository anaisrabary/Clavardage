package com.DeRivasRabary.insa;

import com.DeRivasRabary.insa.user.LocalUser;
import com.DeRivasRabary.insa.user.User;
import com.DeRivasRabary.insa.user.UserList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */

// TODO : lancer les tests
public class UserTest extends TestCase {


    public void testUserCreation(){
        User user = new User("Jeannot","127.0.0.1");
        assertEquals("Jeannot", user.pseudo);
        assertEquals("127.0.0.1",user.ip);
    }


    public void testUserDisplay(){
        User user = new User("Jeannot","127.0.0.1");
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1", user.toString());
    }


    public void testLocalUserCreation(){
        LocalUser localUser = new LocalUser("Anaisseee","127.0.0.2",true);
        assertEquals("Anaisseee",localUser.pseudo);
        assertEquals("127.0.0.2",localUser.ip);
        assertEquals(true,localUser.actif);
    }

    public void testChangeStateOfLocalUser(){
        LocalUser localUser = new LocalUser("Bloop","127.0.0.1",true);
        assertEquals(true,localUser.actif);
        localUser.chanceEtatActif(false);
        assertEquals(false,localUser.actif);
    }

    public void testDisplayAUserList(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        User user3 = new User("Paulo","127.0.0.3");
        ArrayList<User> listeUser = new ArrayList<User>();
        listeUser.add(user1);listeUser.add(user2);listeUser.add(user3);
        String message = listeUser.toString();
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1\npseudo : Pierrot, ip : 127.0.0.2\nPseudo : Paulo, ip : 127.0.0.3",message);
    }

    public void testCreateUserListWithOneUserEachTime(){
        User user = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        UserList userList = new UserList(user);
        userList.userList.add(user2);
        String message = "" ;
        for (User u:userList.userList) {
            message = message + u.toString();
        }
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1\npseudo : Pierrot, ip : 127.0.0.2\n",message);
    }


    public void testCreateUserListWithAList(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        ArrayList<User> listeUser = new ArrayList<User>();
        listeUser.add(user1); listeUser.add(user2);
        UserList userList = new UserList(listeUser);
        String message = userList.toString();
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1\npseudo : Pierrot, ip : 127.0.0.2\n",message);
    }


    public void testGetUserByIp(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2); userList.userList.add(user3);
        User userRequested ;
        try {
            userRequested = userList.getUserByIp("127.0.0.1");
            assertEquals("Jeannot", userRequested.pseudo);
            assertEquals("127.0.0.1", userRequested.ip);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le premier User - KO");
            assertEquals(false,true);
        }
        try {
            userRequested = userList.getUserByIp("127.0.0.2");
            assertEquals("Pierrot", userRequested.pseudo);
            assertEquals("127.0.0.2", userRequested.ip);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le deuxieme User - KO");
            assertEquals(false,true);
        }
        try {
            userRequested = userList.getUserByIp("127.0.0.2");
            assertEquals("Paulo", userRequested.pseudo);
            assertEquals("127.0.0.3", userRequested.ip);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le troisieme User - KO ");
            assertEquals(false,true);
        }
        try {
            userRequested = userList.getUserByIp("233468766");
            assertEquals(false,true);
        }
        catch (Exception e){
            assertEquals(true,true);
            System.out.println("Exception sur GetUserByIP pour un utilisateur inexistant - OK");
        }
    }


    public void testGetUserByPseudo(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2); userList.userList.add(user3);
        User userToFound ;

        try {
        userToFound = userList.getUserByPseudo("Jeannot");
        assertEquals("Jeannot",userToFound.pseudo);
        assertEquals("127.0.0.1",userToFound.ip); }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le premier User - KO ");
            assertEquals(false,true);
        }

        try{
        userToFound = userList.getUserByPseudo("Pierrot");
        assertEquals("Pierrot",userToFound.pseudo);
        assertEquals("127.0.0.2",userToFound.ip);}
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le deuxieme User - KO ");
            assertEquals(false,true);
        }

        try {
            userToFound = userList.getUserByPseudo("Paulo");
            assertEquals("Paulo", userToFound.pseudo);
            assertEquals("127.0.0.3", userToFound.ip);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le troisieme User - KO ");
            assertEquals(false,true);
        }

        try{
            userToFound = userList.getUserByPseudo("Fred");
            assertEquals("Fred", userToFound.pseudo);
            assertEquals("127.0.0.4", userToFound.ip);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour un user inexistant - OK ");
            assertEquals(true,true);
        }
    }



    public void testUpdatePseudoByIp(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2); userList.userList.add(user3);
        User userModified = new User("","") ;
        /* Cas où un utilisateur existe */
        try {
            userList.updatePseudobyIp("127.0.0.2", "Fred");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception levée lors de l'update Pseudo by Ip - KO");
        }
        try {
            userModified = userList.getUserByIp("127.0.0.2");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception levée lors de l'update Pseudo by Ip - KO");
        }
        assertEquals("Fred",userModified.pseudo);

        /* Cas où l'utilisateur n'existe pas */
        try{
            userList.updatePseudobyIp("127.0.0.4","boo");
            assertEquals("false", "true");
        }
        catch (Exception e){
            e.printStackTrace();
            assertEquals("true","true");
        }
    }
}




/**
 * Create the test case
 *
 * @param testName name of the test case
 */
    /*
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    /*
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    */
/**
 * Rigourous Test :-)
 */
    /*
    public void testApp()
    {
        assertTrue( true );
    }
    */
