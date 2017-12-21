package com.DeRivasRabary.insa.network;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkResourcefull {


    public static InetAddress getLocalAdress() {
        try
        {
            boolean trouve = false;
            String ip="" ;
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (!trouve || e.hasMoreElements()) {
                Enumeration<InetAddress> i = e.nextElement().getInetAddresses();
                while (i.hasMoreElements()) {
                    InetAddress a = i.nextElement();
                    if (a.isSiteLocalAddress()) {
                        trouve = true;
                        ip=a.getHostAddress();
                    }
                }
            }
            return InetAddress.getByName(ip) ;
        } catch (Exception e){
            System.err.println("Erreur dans getLocalAdress. Impossible de trouver ip privée locale.\n" +
                    "Etes vous bien connecté au réseau ?");
            return null;
        }
    }

    public static InetAddress getBroadcastAddress() {
        String ip;
        try {
            Enumeration<java.net.NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                java.net.NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                for(InterfaceAddress ifaceAddr : iface.getInterfaceAddresses()) {
                    InetAddress bcast = ifaceAddr.getBroadcast();
                    if(bcast == null)
                        continue;
                    return bcast;
                }

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}



