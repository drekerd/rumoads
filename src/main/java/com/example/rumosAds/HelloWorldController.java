package com.example.rumosAds;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/cenas")
    public String testHello(Model model){
        String name = "mario";
        model.addAttribute("cenas", name);
        return "helloTest";
    }
}
