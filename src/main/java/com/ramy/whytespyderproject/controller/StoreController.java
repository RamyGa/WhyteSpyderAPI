package com.ramy.whytespyderproject.controller;


import com.ramy.whytespyderproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(path = "/")
    public ResponseEntity uploadDataToH2() throws IOException {

        return storeService.uploadFile();
    }

    @GetMapping(path = "/get_all",produces = "application/json")
    public ResponseEntity getAllStores(){

        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping(path = "/store_number/{store}",produces = "application/json")
    public ResponseEntity getByStoreNumber(@PathVariable int store){
        return ResponseEntity.ok(storeService.getByStoreNumber(store));
    }

    @GetMapping(path = "/state/{state}",produces = "application/json")
    public ResponseEntity getAllStoresByState(@PathVariable String state){
        return ResponseEntity.ok(storeService.getAllStoresByState(state));
    }

}
