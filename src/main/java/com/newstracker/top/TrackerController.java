package com.newstracker.top;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

//import com.tutorialspoint.demo.model.Product;

@Controller
public class TrackerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String getProductList(Model model) {
        TrackerOperations trackerOps = new TrackerOperations();
        trackerOps.retrieveTop10();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return "index.html";

    }
}