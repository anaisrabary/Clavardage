package com.DeRivasRabary.insa.Test;

import com.DeRivasRabary.insa.model.packet.factory.PacketFactory;
import com.DeRivasRabary.insa.model.packet.Bye;
import com.DeRivasRabary.insa.model.packet.Hello;
import com.DeRivasRabary.insa.model.packet.Message;
import junit.framework.TestCase;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Unit test for simple App.
 */

public class PacketTest extends TestCase {


    public void testCreateMessagePacket() throws UnknownHostException {
        Message packetMessage = new PacketFactory().createPacketMessage(
                InetAddress.getByName("127.0.0.1"),InetAddress.getByName("127.0.0.2"),
                "Me","you","HelloWorld");
        assertEquals("/127.0.0.1",packetMessage.getIpSender().toString());
        assertEquals("/127.0.0.2", packetMessage.getIpReceiver().toString());
        assertEquals("Me",packetMessage.getPseudoEmmeteur());
        assertEquals("you",packetMessage.getPseudoDestinataire());
        assertEquals("HelloWorld",packetMessage.getData());
        System.out.println("Heure du message à vérifier manuellement : " + packetMessage.getDate());
    }

    public void testCreateByePacket() throws UnknownHostException {
        Bye byeMessage = new PacketFactory().createPacketBye(
                InetAddress.getByName("127.0.0.1"),InetAddress.getByName("192.168.1.1"),"Fred","Bloop");
        assertEquals("/127.0.0.1",byeMessage.getIpSender().toString());
        assertEquals("/192.168.1.1",byeMessage.getIpReceiver().toString());
        assertEquals("Fred",byeMessage.getPseudoEmmeteur());
        assertEquals("Bloop",byeMessage.getPseudoDestinataire());
        System.out.println("Heure du bye à vérifier manuellement : " + byeMessage.getDate());
    }

    public void testCreateHelloPacket() throws UnknownHostException {
        Hello helloMessage = new PacketFactory().createPacketHello(
                InetAddress.getByName("127.0.0.1"),InetAddress.getByName("192.168.1.1"),"Fred","Bibi", Hello.Control_type.ACK,1);
        assertEquals("/127.0.0.1",helloMessage.getIpSender().toString());
        assertEquals("/192.168.1.1",helloMessage.getIpReceiver().toString());
        assertEquals("Fred",helloMessage.getPseudoEmmeteur());
        assertEquals("Bibi",helloMessage.getPseudoDestinataire());
        assertEquals(Hello.Control_type.ACK,helloMessage.getType());
        assertEquals(1,helloMessage.getData());
        System.out.println("Heure du hello à vérifier manuellement : " + helloMessage.getDate());
    }


    public void testDisplayMessagePacket() throws UnknownHostException {
        Message packetMessage = new PacketFactory().createPacketMessage(
                InetAddress.getByName("192.168.0.1"),InetAddress.getByName("192.168.0.2"),"Jeannot","Pierrot", "HelloWorld");
        assertEquals("Type : Message\nIPSource : /192.168.0.1\n" +
                        "IPDestination : /192.168.0.2\n" +
                        "Date : " + packetMessage.getDate() + "\n" +
                        "PseudoEmetteur : Jeannot\n" +
                        "PseudoDestinataire : Pierrot\n" +
                        "Message : HelloWorld\n"
                        ,packetMessage.toString());
    }

    public void testDisplayByePacket() throws UnknownHostException {
        Bye byeMessage = new PacketFactory().createPacketBye(
                InetAddress.getByName("192.168.3.1"),InetAddress.getByName("192.168.3.2"),"Fred",
                "Toto");
        assertEquals("Type : Bye\nIPSource : /192.168.3.1\n" +
                        "IPDestination : /192.168.3.2\n" +
                        "Date : " + byeMessage.getDate() + "\n" +
                        "PseudoEmetteur : Fred\n" +
                        "PseudoDestinataire : Toto\n"
                        ,byeMessage.toString());
    }

    public void testDisplayHelloPacket() throws UnknownHostException {
        Hello helloMessage = new PacketFactory().createPacketHello(
                InetAddress.getByName("192.168.10.10"),InetAddress.getByName( "192.168.10.11"), "Fred",
                "Titi", Hello.Control_type.ACK,1);
        assertEquals("Type : Hello\n" +
                        "IPSource : /192.168.10.10\n" +
                        "IPDestination : /192.168.10.11\n" +
                        "Date : " + helloMessage.getDate() + "\n" +
                        "PseudoEmetteur : Fred\n" +
                        "PseudoDestinataire : Titi\n" +
                        "ControlType : ACK\n" +
                        "Data : 1\n"
                , helloMessage.toString());
    }



    public static void main(String[] args) {

    }

}


