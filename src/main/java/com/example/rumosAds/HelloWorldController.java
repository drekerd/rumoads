package com.example.rumosAds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/test")
    public String testHello(){
        return "helloTest";
    }
}
