package com.example.rumosAds;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddsController {
    private static final Logger LOGGER = Logger.getLogger("AddsController");

    //@TODO refractor Adds name

    @Autowired
    private AddsService service;

    //this method is only to check if project is running when something is not working
    @RequestMapping("/hello")
    public String testHello(Model model) {
        LocalTime time = LocalTime.now();
        String name = "Mário";
        model.addAttribute("cenas", name + time);
        return "helloTest";
    }

    @RequestMapping("/admin")
    public String getAdds(Model model) {
        //persistOnInitialize();
        LOGGER.info("RequestMapping admin: started");
        model.addAttribute("addsFromBE", service.getAdds());
        //model.addAttribute("lastAddId",service.getAdds().size()+1);

        LOGGER.info(Integer.toString(service.getAdds().size()+1));

        return "adminCrud";
    }

    @RequestMapping("/new-add-page")
    public String getNewAddPage(Model model) {
        //persistOnInitialize();
        LOGGER.info("RequstMapping AddNewAddPage: started");
        model.addAttribute("lastAddId",service.getAdds().size()+1);
        return "newAdd";
    }

    @PostMapping("/newAdd")
    public ModelAndView newAdd(Adds add, Model model){
        LOGGER.info(add.toString());
        persistAdd(add);
        return new ModelAndView("adminCrud");
    }

    private void persistAdd(Adds add) {
        if(service.getAdds().contains(add)){
            return;
        }else{
            Adds addToPersist = new Adds();
            addToPersist.setAddId(service.getAdds().size()+1);
            addToPersist.setAddName(add.getAddName());
            addToPersist.setAddDescription(add.getAddDescription());
            addToPersist.setAddPrice(add.getAddPrice());
            service.getAdds().add(addToPersist);
        }
    }

    // @TODO Update Add

    // @TODO Delete Add

}
