/*package com.DeRivasRabary.insa.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClients implements Runnable {
    private ServerSocket serverSocket;
    private IncomingMessageListener incomingMessageListener;
    private Socket socket;

    public AcceptClients(ServerSocket socket,IncomingMessageListener listener){
        this.serverSocket = socket;
        this.incomingMessageListener = listener;
    }


    @Override
    public void run(){
        Socket socket;
        try {
            while(true){
                socket = this.serverSocket.accept(); //  block current thread until client asks for a connection

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                this.incomingMessageListener.onNewIncomingMessage(reader.readLine());

                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
*/
