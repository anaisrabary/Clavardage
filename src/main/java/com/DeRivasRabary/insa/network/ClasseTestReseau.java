package com.DeRivasRabary.insa.network;


import java.net.*;
import java.util.Enumeration;

public class ClasseTestReseau {

    /**
     * Renvoie la première adresse IP privée trouvée du PC. Attention aux PC connectés sur plusieurs interfaces...
     * A améliorer pour intégrer plusieurs interfaces. Mais fonctionne dans la majorité des cas.
     * TODO : à déplacer ailleurs, c'est une classe temporaire
     * @return String d'une ipprivée
     */
    public static String getLocalAdress() {
        try
        {
            boolean trouve = false;
            String ip = "" ;
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
            return ip ;
        } catch (Exception e){
            System.err.println("Erreur dans getLocalAdress. Impossible de trouver ip privée locale.\n" +
                    "Etes vous bien connecté au réseau ?");
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(getLocalAdress());
    }
}