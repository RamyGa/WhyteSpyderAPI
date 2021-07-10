package com.ramy.whytespyderproject.loadData;

import com.ramy.whytespyderproject.entity.StoreEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Component
public class CSVLoader  {


    public  String TYPE = "text/csv";



    public CSVLoader() {
    }

    public boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) { return false;}
        return true;
    }




    public  List<StoreEntity> csvToStores(InputStream is) throws FileNotFoundException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<StoreEntity> storeEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                StoreEntity storeEntity = new StoreEntity(
                        Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1),
                       csvRecord.get(2),
                       csvRecord.get(3),
                        csvRecord.get(4),
                        csvRecord.get(5),
                        Double.parseDouble(csvRecord.get(6)),
                        Double.parseDouble(csvRecord.get(7)),
                        csvRecord.get(8),
                        csvRecord.get(9),
                        csvRecord.get(10),
                        csvRecord.get(11)

                );
                storeEntities.add(storeEntity);}
            return storeEntities;
        }catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }}}





