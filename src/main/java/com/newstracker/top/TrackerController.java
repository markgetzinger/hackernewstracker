package com.newstracker.top;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TrackerController {
    TrackerOperations trackerOps = new TrackerOperations();

        @GetMapping("/")
    public String calcResults(Model model) {
        model.addAttribute("hackerNewsStories", trackerOps.getTopStories(10));
        return "result";
    }

}