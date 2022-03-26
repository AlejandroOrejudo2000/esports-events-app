package es.urj.dad.rest.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import com.opencsv.CSVWriter;

import org.springframework.stereotype.Service;

@Service
public class CSVService {
    
    private final String filepath = "./file.csv";

    public String generateCSV(List<String[]> stringArray) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filepath));
        writer.writeAll(stringArray);
        writer.close();
        return filepath;
    }
    

}
