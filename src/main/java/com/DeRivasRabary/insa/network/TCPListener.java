package com.DeRivasRabary.insa.network;

import com.DeRivasRabary.insa.model.packet.PacketManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPListener extends Thread{
 protected ServerSocket server ;
 private Socket chatsocket ;

 public TCPListener(ServerSocket server) {
     this.server = server;
 }

    @Override
    public void run(){
     try {
         while(true){

             chatsocket = server.accept();
             ObjectInputStream is = new ObjectInputStream(chatsocket.getInputStream());
             PacketManager p = (PacketManager) is.readObject();
             managePacket(p);
         }

     }catch (EOFException e) {
         System.out.println("connection ended");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }catch (IOException e){
         e.printStackTrace();
     }

    }


    protected void closeConnection() {
        try {
            chatsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void managePacket(PacketManager p);
}

