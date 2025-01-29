package com.Feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackController {
    @GetMapping("/")
    public String feedbackForm(){
        return "index";
    }

//    @GetMapping("/saved")
//    public String saved(){
//        return "saved";
//    }
}
