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
public class UserTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    /*
    public AppTest( String testName )
    {
        super( testName );
    }*/

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }




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


    public void testCreateUserListWithOneUser(){
        User user = new User("Jeannot","127.0.0.1");
        UserList userList = new UserList(user);
        for (User u:userList.userList) {
            u.toString();
        }
    }

    public void testCreateUserListWithAList(){
        User user1 = new User("Jeannot","127.0.0.1");
        User user2 = new User("Pierrot","127.0.0.2");
        User user3 = new User("Paulo","127.0.0.3");
        ArrayList<User> listeUser = new ArrayList<User>();
        listeUser.add(user1);listeUser.add(user2);listeUser.add(user3);
    }

    public void testDisplayAUserList(){

    }


    public void testGetUserByIp(){

    }

    public void testGetIpByPseudo(){

    }

    public void testUpdatePseudoByIp(){

    }



}
