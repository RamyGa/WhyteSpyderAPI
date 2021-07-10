package com.ramy.whytespyderproject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
