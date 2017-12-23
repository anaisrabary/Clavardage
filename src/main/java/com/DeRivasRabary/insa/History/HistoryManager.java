package com.DeRivasRabary.insa.History;

import com.DeRivasRabary.insa.model.packet.File;
import com.DeRivasRabary.insa.model.packet.Message;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class HistoryManager {

    public String filename;
    private char sep = ',';

    public HistoryManager(String ipDest) throws IOException {
        this.filename = ipDest + "-data.csv";
        // if fichier exist ne pas le créer
        java.io.File f = new java.io.File(filename);
        if (!f.isFile()) {
            CSVWriter writer = new CSVWriter(new FileWriter(filename));
            writer.close();
        }

    }

    public void addMessage(Message message) throws IOException {
        // il existe une méthode constructeur csvwriter plus complète pour rendre le programme plus robuste si besoin
        CSVWriter writer = new CSVWriter(new FileWriter(filename, true));
        String messageStored = message.getPseudoEmmeteur() + sep + message.getDate() + sep + message.getData();
        String [] record = messageStored.split(",");

        writer.writeNext(record);

        writer.close();
    }


}
