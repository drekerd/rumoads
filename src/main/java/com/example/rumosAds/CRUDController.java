package com.example.rumosAds;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CRUDController {
    private static final Logger LOGGER = Logger.getLogger("CRUDController");

    @Autowired
    private AddsService service;

    @RequestMapping("/hello")
    public String testHello(Model model) {
        String name = "mario";
        model.addAttribute("cenas", name);
        return "helloTest";
    }

    @RequestMapping("/admin")
    public String getAdds(Model model) {
        //persistOnInitialize();

        model.addAttribute("addsFromBE", service.getAdds());

        return "adminCrud";
    }

}
