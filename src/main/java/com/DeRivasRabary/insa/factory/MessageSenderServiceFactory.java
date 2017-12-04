/*package com.DeRivasRabary.insa.factory;

//import com.DeRivasRabary.insa.network.TCPMessageSenderManager;
import com.DeRivasRabary.insa.network.UDPMessageSenderManager;

public class MessageSenderServiceFactory implements MessageServiceFactory<MessageSenderService> {

    @Override
    public MessageSenderService onSend() {
        return  new UDPMessageSenderManager();
        //return new TCPMessageSenderManager();
    }

    @Override
    public MessageSenderService onReceive() {
        return new UDPMessageSenderManager();
    }
}
*/
