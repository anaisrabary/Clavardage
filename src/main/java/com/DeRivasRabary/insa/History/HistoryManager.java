package com.DeRivasRabary.insa.History;

import com.DeRivasRabary.insa.model.packet.Message;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class HistoryManager {

    public String filename;
    private char sep = ',';

    public HistoryManager(String ipDest) throws IOException {
        //nomdufichier @IPdestinataire.csv
        //contient historique des conversations
        this.filename = ipDest+"-data.csv";

            CSVWriter writer = new CSVWriter(new FileWriter(filename));

            //close the writer
            writer.close();
    }

    public void addMessage(Message message) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename, true));
        String messageStored = message.getPseudoEmmeteur() + sep + message.getDate() + sep + message.getData();
        String [] record = messageStored.split(",");

        writer.writeNext(record);

        writer.close();
    }


}
