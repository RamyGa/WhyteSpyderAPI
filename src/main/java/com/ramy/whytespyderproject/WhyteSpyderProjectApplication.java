package com.ramy.whytespyderproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URISyntaxException;

@SpringBootApplication
public class WhyteSpyderProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(WhyteSpyderProjectApplication.class, args);

    }

}
