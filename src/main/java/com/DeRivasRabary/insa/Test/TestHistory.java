package com.DeRivasRabary.insa.Test;

import com.DeRivasRabary.insa.History.HistoryManager;
import com.DeRivasRabary.insa.model.packet.Message;
import junit.framework.TestCase;

import java.io.*;
import java.net.InetAddress;

public class TestHistory extends TestCase {


    public void testCreateNewHistory(){
        try {
            HistoryManager localHistory = new HistoryManager("127.0.0.1");
            Message message = new Message(InetAddress.getByName("127.0.0.0"), InetAddress.getByName("127.0.0.1"),
                    "Emetteur", "Recepteur", "Voici le message a sauvegarder");
            Message message1 = new Message(InetAddress.getByName("127.0.0.1"), InetAddress.getByName("127.0.0.0"),
                    "Recepteur", "Emetteur", "2e message à sauvegarder");
            localHistory.addMessage(message);
            localHistory.addMessage(message1);
            File f = new File(localHistory.filename);
            assertEquals(true,f.isFile());
        }
        catch (Exception e) {
            System.err.println("Exception dans testCreateNewHistory");
            assertTrue(false);
        }
    }

    public void testAppendNewMessageHistory(){
        try {
            HistoryManager localHistory = new HistoryManager("127.0.0.1");
            File f = new File(localHistory.filename);
            FileReader fis = new FileReader(f);
            BufferedReader br = new BufferedReader(fis);
            Message message1 = new Message(InetAddress.getByName("127.0.0.1"), InetAddress.getByName("127.0.0.0"),
                    "Recepteur", "Emetteur", "MessageTestAppendHistory");
            localHistory.addMessage(message1);
            String line;
            boolean trouve = false ;
            int nbline = 1 ;
            int linefound =0 ;
            while ((line = br.readLine()) != null && !trouve) {
                nbline++;
                if(br.readLine().contains("MessageTestAppendHistory")){
                    trouve=true;
                    linefound = nbline ;
                }
            }
            br.close();
            assertEquals(true,trouve && (linefound==nbline));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
