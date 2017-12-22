package com.DeRivasRabary.insa.History;

import com.opencsv.CSVWriter;

import java.io.FileWriter;

public class HistoryManager {

    public HistoryManager(){
        //nomdufichier @IPdestinataire.csv
        //contient historique des conversations
        String csv = "data.csv";
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csv));

            //Create record
            String[] record = "4,David,Miller,Australia,30".split(",");
            //Write the record to file
            writer.writeNext(record);

            //close the writer
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
