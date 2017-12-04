package com.DeRivasRabary.insa;

import com.DeRivasRabary.insa.factory.PacketFactory;
import com.DeRivasRabary.insa.network.packet.Message;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    }

    public void testCreateByePacket(){

    }

    public void testCreateHelloPacket(){

    }

}
