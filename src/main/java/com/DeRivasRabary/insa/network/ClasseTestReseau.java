package com.DeRivasRabary.insa.network;


import java.net.InetAddress;

public class ClasseTestReseau {

    public static void main(String[] args) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String loopbackIp = InetAddress.getLoopbackAddress().getHostAddress();
            System.out.println("IP : " + ip);
            System.out.println("LoopBackIP : " + loopbackIp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
