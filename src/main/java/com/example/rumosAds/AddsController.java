package com.example.rumosAds;

import java.time.LocalTime;
import java.util.logging.Logger;

import com.example.messages.MessageProducer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jms.Queue;

@Controller
public class AddsController {
    private static final Logger LOGGER = Logger.getLogger("AddsController");

    //@TODO refractor Adds name

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    private AddsService service;

    MessageProducer messageProducer = new MessageProducer();

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

        model.addAttribute("addsFromBE", service.getAdds());
        String serializedAdd = serializationToJson(persistAdd(add));

        //its just returning the last added movie for now. Its just an "hello world" to start
        jmsTemplate.convertAndSend(queue, serializedAdd);
        LOGGER.info("message : PUBLISHED, with message " + serializedAdd);

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping("/admin/delete{id}")
    public ModelAndView deleteAdd(long id, Model model) {
        LOGGER.info("RequestMapping admin DELETE : starts, ID = " + id);
        deleteAdd(id);
        model.addAttribute("addsFromBE", service.getAdds());

        return new ModelAndView("redirect:/admin");
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

    private Adds persistAdd(Adds add) {
        if (service.getAdds().contains(add)) {
            return null;
        } else {
            Adds addToPersist = new Adds();
            addToPersist.setAddId(service.getAdds().size() + 1);
            addToPersist.setAddName(add.getAddName());
            addToPersist.setAddDescription(add.getAddDescription());
            addToPersist.setAddPrice(add.getAddPrice());
            service.getAdds().add(addToPersist);

            return addToPersist;
        }
    }

    private String serializationToJson(Adds add) {
        Gson gson = new Gson();
        return gson.toJson(add);
    }

}
