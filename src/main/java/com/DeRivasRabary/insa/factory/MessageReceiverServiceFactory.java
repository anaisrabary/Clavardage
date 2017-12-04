/*package com.DeRivasRabary.insa.factory;

import com.DeRivasRabary.insa.network.TCPMessageReceiverManager;
import com.DeRivasRabary.insa.network.UDPMessageReceiverManager;

public class MessageReceiverServiceFactory implements MessageServiceFactory<MessageReceiverService> {
    @Override
    public MessageReceiverService onTCP() {
        return  new UDPMessageReceiverManager();
        //return new TCPMessageReceiverManager();
    }


    @Override
    public MessageReceiverService onUDP() {
        return new UDPMessageReceiverManager();
    }

}
*/
