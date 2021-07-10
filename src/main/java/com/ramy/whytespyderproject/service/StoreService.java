package com.ramy.whytespyderproject.service;

import com.ramy.whytespyderproject.WhyteSpyderProjectApplication;
import com.ramy.whytespyderproject.dto.StoreDTO;
import com.ramy.whytespyderproject.entity.StoreEntity;
import com.ramy.whytespyderproject.loadData.CSVLoader;
import com.ramy.whytespyderproject.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class StoreService {


    private final StoreRepo repository;
    private final CSVLoader csvLoader;

    @Autowired
    public StoreService(StoreRepo repository, CSVLoader csvLoader) {
        this.repository = repository;
        this.csvLoader = csvLoader;
    }
    @EventListener(ApplicationReadyEvent.class)//will initially run when the app boots up
    public void uploadFile() throws URISyntaxException, IOException {//will upload the csv file data to H2

        File file = new File(WhyteSpyderProjectApplication.class.getResource("/ogp_stores.csv").toURI());//pointing to local csv file
        FileInputStream inputStream = new FileInputStream(file);//reads file data
        MultipartFile DATA_FILE = new MockMultipartFile(file.getName(), file.getName(), "text/csv", inputStream);//conversion from File to MultipartFile
        String message = "";
        if (csvLoader.hasCSVFormat(DATA_FILE)) {//if content type is text/csv..
            try {
                save(DATA_FILE);//..save the file data to H2
                message = "Uploaded the file successfully: " + DATA_FILE.getOriginalFilename();
                System.out.println(ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \""));
            } catch (Exception e) {
                message = "Could not upload the file: " + DATA_FILE.getOriginalFilename() + "!";
                System.out.println(ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" "+ message +" \""));

            }
        }
        else {
            message = "Please upload a csv file!";
            System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" "+ message +" \""));
        }


    }

    public void save(MultipartFile file){
        try{
            List<StoreEntity> storeEntities = csvLoader.csvToStores(file.getInputStream());
            repository.saveAll(storeEntities);
        } catch (IOException e){
            throw new RuntimeException("failed to store csv data to H2: " + e.getMessage());
        }
    }

    public List<StoreDTO> getAllStores() {
        List<StoreEntity> storeEntities = repository.findAll();
        List<StoreDTO> storeDTOS = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntities) {
            StoreDTO storeDTO =
                    new StoreDTO(
                            storeEntity.getStore(),
                            storeEntity.getCbsa(),
                            storeEntity.getAddress(),
                            storeEntity.getCity(),
                            storeEntity.getState(),
                            storeEntity.getZip(),
                            storeEntity.getLat(),
                            storeEntity.getLon(),
                            storeEntity.getCcia(),
                            storeEntity.getCapis(),
                            storeEntity.getOgpMarketName(),
                            storeEntity.getStatus()
                            );
            storeDTOS.add(storeDTO);
        }

        return storeDTOS;
    }


    public StoreDTO getByStoreNumber(int store) {

        StoreEntity storeEntity = repository.findByStore(store);

        StoreDTO storeDTO = new StoreDTO(storeEntity.getStore(),
                storeEntity.getCbsa(),
                storeEntity.getAddress(),
                storeEntity.getCity(),
                storeEntity.getState(),
                storeEntity.getZip(),
                storeEntity.getLat(),
                storeEntity.getLon(),
                storeEntity.getCcia(),
                storeEntity.getCapis(),
                storeEntity.getOgpMarketName(),
                storeEntity.getStatus());
       return storeDTO;

    }
    public List<StoreDTO> getAllStoresByState(String state) {
        List<StoreEntity> storeEntities = repository.findAllByState(state);
        List<StoreDTO> storeDTOS = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntities) {
            StoreDTO storeDTO =
                    new StoreDTO(
                            storeEntity.getStore(),
                            storeEntity.getCbsa(),
                            storeEntity.getAddress(),
                            storeEntity.getCity(),
                            storeEntity.getState(),
                            storeEntity.getZip(),
                            storeEntity.getLat(),
                            storeEntity.getLon(),
                            storeEntity.getCcia(),
                            storeEntity.getCapis(),
                            storeEntity.getOgpMarketName(),
                            storeEntity.getStatus()
                    );
            storeDTOS.add(storeDTO);
        }

        return storeDTOS;
    }
}
