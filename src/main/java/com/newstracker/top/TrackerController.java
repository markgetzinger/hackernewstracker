package com.newstracker.top;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//import com.tutorialspoint.demo.model.Product;

@Controller
public class TrackerController {
    @Autowired
    RestTemplate restTemplate;

    TrackerOperations trackerOps = new TrackerOperations();

    @GetMapping("/")
    public String getProductList(Model model) {
        trackerOps.buildFTLTemplate(trackerOps.getTopStories(10));

        return "index.html";


    }
    @PostMapping("/calc")
    public String calcResults(Model model) {
        trackerOps.buildFTLTemplate(trackerOps.getTopStories(10));
        //model.addAttribute("news",values);
        return "result";
    }

}