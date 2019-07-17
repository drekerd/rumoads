package com.example.rumosAds;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/admin")
    public String getAdds(Model model) {
        LOGGER.info("RequestMapping admin GET: started");
        model.addAttribute("addsFromBE", service.getAdds());

        return "adminCrud";
    }

    @PostMapping("/admin")
    public ModelAndView newAdd(Adds add, Model model) {
        LOGGER.info("RequestMapping admin POST" + add.toString());
        persistAdd(add);
        model.addAttribute("addsFromBE", service.getAdds());

        return new ModelAndView("adminCrud");
    }

    @RequestMapping("/admin/delete{id}")
    public ModelAndView deleteAdd(long id, Model model) {
        LOGGER.info("RequestMapping admin DELETE : starts, ID = " + id);
        deleteAdd(id);
        model.addAttribute("addsFromBE", service.getAdds());

        return new ModelAndView("adminCrud");
    }

    // @TODO Update Add

    private void deleteAdd(long id) {
        if (service.getAdds().isEmpty()) {
            LOGGER.warning("Delete ADD: ended with ERROR : LIST IS EMPTY");
            return;
        } else {
            for (Adds add : service.getAdds()) {
                if (add.getAddId() == id) {
                    service.getAdds().remove(add);
                    LOGGER.warning("Delete ADD: ended with SUCCESS : ADD " + add.getAddName() + " DELETED");
                    return;
                } else
                    LOGGER.warning("Delete ADD: ended with ERROR : NO ADD FOUND");
            }
        }
    }

    private void persistAdd(Adds add) {
        if (service.getAdds().contains(add)) {
            return;
        } else {
            Adds addToPersist = new Adds();
            addToPersist.setAddId(service.getAdds().size() + 1);
            addToPersist.setAddName(add.getAddName());
            addToPersist.setAddDescription(add.getAddDescription());
            addToPersist.setAddPrice(add.getAddPrice());
            service.getAdds().add(addToPersist);
        }
    }


}
