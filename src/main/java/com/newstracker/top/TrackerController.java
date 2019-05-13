package com.newstracker.top;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import com.newstracker.top.TrackerOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

//import com.tutorialspoint.demo.model.Product;

@RestController
public class TrackerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String getProductList() {
        TrackerOperations trackerOps = new TrackerOperations();
        String lol = trackerOps.retrieveTop500();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(
                "https://hacker-news.firebaseio.com/v0/topstories.json", HttpMethod.GET, entity, String.class).getBody();

    }


    @PostMapping("/top")
        public String getTopTen(){

            return "template";
        }



}