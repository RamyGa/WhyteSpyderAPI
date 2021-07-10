package com.ramy.whytespyderproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {


    private int store;
    private String cbsa;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double lat;
    private double lon;
    private String ccia;
    private String capis;
    private String ogpMarketName;
    private String status;



}
