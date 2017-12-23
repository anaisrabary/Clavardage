package com.DeRivasRabary.insa.Test;

import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */

import com.DeRivasRabary.insa.model.User;
import com.DeRivasRabary.insa.model.UserList;
import junit.framework.TestCase;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserTest extends TestCase {


    public void testUserCreation() throws UnknownHostException {
        User user = new User("Jeannot", InetAddress.getByName("127.0.0.1"));
        assertEquals("Jeannot", user.getPseudo());
        assertEquals("/127.0.0.1",user.getIPAdress().toString());
    }


    public void testUserDisplay() throws UnknownHostException {
        User user = new User("Jeannot", InetAddress.getByName("127.0.0.1"));
        assertEquals("pseudo : Jeannot, ip : /127.0.0.1\n", user.toString());
    }



    public void testDisplayAUserList() throws UnknownHostException {
        User user1 = new User("Jeannot", InetAddress.getByName("127.0.0.1"));
        User user2 = new User("Pierrot", InetAddress.getByName("127.0.0.2"));
        User user3 = new User("Paulo", InetAddress.getByName("127.0.0.3"));
        ArrayList<User> listeUser = new ArrayList<User>();
        listeUser.add(user1);
        listeUser.add(user2);
        listeUser.add(user3);
        String message = listeUser.toString();
        assertEquals("[pseudo : Jeannot, ip : /127.0.0.1\n, pseudo : Pierrot, ip : /127.0.0.2\n, pseudo : Paulo, ip : /127.0.0.3\n]", message);
    }

/*
    public void testCreateUserListWithOneUserEachTime() {
        User user = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        UserList userList = new UserList(user);
        userList.userList.add(user2);
        String message = "";
        for (User u : userList.userList) {
            message = message + u.toString();
        }
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1\npseudo : Pierrot, ip : 127.0.0.2\n", message);
    }


    public void testCreateUserListWithAList() {
        User user1 = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        ArrayList<User> listeUser = new ArrayList<User>();
        listeUser.add(user1);
        listeUser.add(user2);
        UserList userList = new UserList(listeUser);
        String message = userList.toString();
        assertEquals("pseudo : Jeannot, ip : 127.0.0.1\npseudo : Pierrot, ip : 127.0.0.2\n", message);
    }


    public void testGetUserByIp() {
        User user1 = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2);
        userList.userList.add(user3);
        User userRequested;
        try {
            userRequested = userList.findUserByIp("127.0.0.1");
            assertEquals("Jeannot", userRequested.getPseudo());
            assertEquals("127.0.0.1", userRequested.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le premier User - KO");
            assertEquals(false, true);
        }
        try {
            userRequested = userList.findUserByIp("127.0.0.2");
            assertEquals("Pierrot", userRequested.getPseudo());
            assertEquals("127.0.0.2", userRequested.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le deuxieme User - KO");
            assertEquals(false, true);
        }
        try {
            userRequested = userList.findUserByIp("127.0.0.3");
            assertEquals("Paulo", userRequested.getPseudo());
            assertEquals("127.0.0.3", userRequested.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByIP pour le troisieme User - KO ");
            assertEquals(false, true);
        }
        try {
            userRequested = userList.findUserByIp("233468766");
            assertEquals(false, true);
        } catch (Exception e) {
            assertEquals(true, true);
            System.out.println("Exception sur GetUserByIP pour un utilisateur inexistant - OK");
        }
    }


    public void testGetUserByPseudo() {
        User user1 = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2);
        userList.userList.add(user3);
        User userToFound;

        try {
            userToFound = userList.findUserByPseudoExact("Jeannot");
            assertEquals("Jeannot", userToFound.getPseudo());
            assertEquals("127.0.0.1", userToFound.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le premier User - KO ");
            assertEquals(false, true);
        }

        try {
            userToFound = userList.findUserByPseudoExact("Pierrot");
            assertEquals("Pierrot", userToFound.getPseudo());
            assertEquals("127.0.0.2", userToFound.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le deuxieme User - KO ");
            assertEquals(false, true);
        }

        try {
            userToFound = userList.findUserByPseudoExact("Paulo");
            assertEquals("Paulo", userToFound.getPseudo());
            assertEquals("127.0.0.3", userToFound.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour le troisieme User - KO ");
            assertEquals(false, true);
        }

        try {
            userToFound = userList.findUserByPseudoExact("Fred");
            assertEquals("Fred", userToFound.getPseudo());
            assertEquals("127.0.0.4", userToFound.getIPAdress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception sur GetUserByPseudo pour un user inexistant - OK ");
            assertEquals(true, true);
        }
    }


    public void testUpdatePseudoByIp() {
        User user1 = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList userList = new UserList(user1);
        userList.userList.add(user2);
        userList.userList.add(user3);
        User userModified = new User("", "");
        // Cas où un utilisateur existe
        try {
            userList.updatePseudobyIp("127.0.0.2", "Fred");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception levée lors de l'update Pseudo by Ip - KO");
        }
        try {
            userModified = userList.findUserByIp("127.0.0.2");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception levée lors de l'update Pseudo by Ip - KO");
        }
        assertEquals("Fred", userModified.getPseudo());

        // Cas où l'utilisateur n'existe pas
        try {
            userList.updatePseudobyIp("127.0.0.4", "boo");
            assertEquals("false", "true");
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("true", "true");
        }
    }


    public void testUserListMap() {

    }

    public static void main(String[] args) {
        User user1 = new User("Jeannot", "127.0.0.1");
        User user2 = new User("Pierrot", "127.0.0.2");
        User user3 = new User("Paulo", "127.0.0.3");
        UserList listMap = UserList.createInstance();
        listMap.addUser(user1);
        listMap.addUser(user2);
        listMap.addUser(user3);
        System.out.println("Affichage des users : ");
        System.out.println(listMap.toString());
        System.out.println("recherche user 127.0.0.1");
        try {
            User u1 = listMap.findUserByIp("127.0.0.1");
            System.out.println(u1.toString());
            listMap.updatePseudobyIp("127.0.0.3", "fred");
            System.out.println("127.0.0.1 a pour nouveau pseudo fred");
            System.out.println(listMap.toString());
            System.out.println("******");
            ArrayList<User> users = listMap.findListUserByPseudo("fred");
            System.out.println(users.toString());
        } catch (Exception e) {
            System.err.println("utilisateur pas trouvé");
        }


    }
    */
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