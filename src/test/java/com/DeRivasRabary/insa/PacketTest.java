package com.DeRivasRabary.insa;

import com.DeRivasRabary.insa.factory.PacketFactory;
import com.DeRivasRabary.insa.network.packet.Bye;
import com.DeRivasRabary.insa.network.packet.Hello;
import com.DeRivasRabary.insa.network.packet.Message;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Scanner;


/**
 * Unit test for simple App.
 */
public class PacketTest extends TestCase {


    public void testCreateMessagePacket(){
        Message packetMessage = new PacketFactory().createPacketMessage(
                "127.0.0.1","127.0.0.2","Me","HelloWorld");
        assertEquals("127.0.0.1",packetMessage.ipSender);
        assertEquals("127.0.0.2", packetMessage.ipReceiver);
        assertEquals("Me",packetMessage.pseudo);
        assertEquals("HelloWorld",packetMessage.message);
        System.out.println("Heure du message à vérifier manuellement : " + packetMessage.date);
    }

    public void testCreateByePacket(){
        Bye byeMessage = new PacketFactory().createPacketBye(
                "127.0.0.1","192.168.1.1","Fred");
        assertEquals("127.0.0.1",byeMessage.ipSender);
        assertEquals("192.168.1.1",byeMessage.ipReceiver);
        assertEquals("Fred",byeMessage.pseudo);
        System.out.println("Heure du bye à vérifier manuellement : " + byeMessage.date);
    }

    public void testCreateHelloPacket(){
        Hello helloMessage = new PacketFactory().createPacketHello(
                "127.0.0.1","192.168.1.1","Fred",true);
        assertEquals("127.0.0.1",helloMessage.ipSender);
        assertEquals("192.168.1.1",helloMessage.ipReceiver);
        assertEquals("Fred",helloMessage.pseudo);
        assertEquals(Boolean.TRUE,helloMessage.replyRec);
        System.out.println("Heure du hello à vérifier manuellement : " + helloMessage.date);
    }


    public void testDisplayMessagePacket(){
        Message packetMessage = new PacketFactory().createPacketMessage(
                "192.168.0.1","192.168.0.2","Jeannot","HelloWorld");
        assertEquals("******************************\n" +
                        "Type : Message\nIPSource : 192.168.0.1\n" +
                        "IPDestination : 192.168.0.2\n" +
                        "Date : " + packetMessage.date + "\n" +
                        "Pseudo : Jeannot\n" +
                        "Message : HelloWorld\n******************************\n"
                        ,packetMessage.toString());
    }

    public void testDisplayByePacket(){
        Bye byeMessage = new PacketFactory().createPacketBye(
                "192.168.3.1","192.168.3.2","Fred");
        assertEquals("******************************\n" +
                        "Type : Bye\nIPSource : 192.168.3.1\n" +
                        "IPDestination : 192.168.3.2\n" +
                        "Date : " + byeMessage.date + "\n" +
                        "Pseudo : Fred\n" +
                        "******************************\n"
                        ,byeMessage.toString());
    }

    public void testDisplayHelloPacket(){
        Hello helloMessage = new PacketFactory().createPacketHello(
                "192.168.10.10","192.168.10.11","Fred",true);
        assertEquals("******************************\n" +
                        "Type : Hello\n" +
                        "IPSource : 192.168.10.10\n" +
                        "IPDestination : 192.168.10.11\n" +
                        "Date : " + helloMessage.date + "\n" +
                        "Pseudo : Fred\n" +
                        "ReplyRec : true\n" +
                        "******************************\n"
                        ,helloMessage.toString());
    }

    // TODO : Essayer de transformer un string en paquet
    public static void main(String[] args) {
        Message packetMessage = new PacketFactory().createPacketMessage(
                "192.168.0.1","192.168.0.2","Jeannot","HelloWorld");
        String packetstring = packetMessage.toString();
        Scanner sc = new Scanner(packetstring);
        System.out.println(sc.next());
    }
}
