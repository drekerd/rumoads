package com.example.rumosAds;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.example.clients.Client;
import com.example.clients.ClientService;
import com.example.dao.CategoryDAO;
import com.example.dao.ClientDAO;
import com.example.dao.CoursesDAO;
import com.example.messages.MessageProducer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.categories.*;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Queue;

@Controller
@Service
public class AddsController {
    private static final Logger LOGGER = Logger.getLogger("AddsController");

    //@TODO refractor Adds name
    //@TODO refractor SERVICE CLASSES FOR EACH ONE

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    List<Adds> addsFromDB;

    @Autowired
    private AddsService service;

    @Autowired
    private CoursesDAO coursesDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClientService clientService;

    @PostConstruct
    public void setCategories() {

        LOGGER.info("Adds " + coursesDAO.findAll());
        LOGGER.info("Category " + categoryDAO.findAll());
        clientSort();
    }

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
        this.addsFromDB = coursesDAO.findAll();
        model.addAttribute("addsFromBE", addsFromDB);
        model.addAttribute("categoryList", categoryDAO.findAll());

        return "adminCrud";
    }

    @PostMapping("/admin")
    public ModelAndView newAdd(Adds add, Model model) {
        LOGGER.info("RequestMapping admin POST" + add.toString());

        persistAdd(add);

        LOGGER.info("message : PUBLISHED, with message ");

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping("/admin/delete{id}")
    public ModelAndView deleteAdd(long id, Model model) {
        LOGGER.info("RequestMapping admin DELETE : starts, ID = " + id);
        deleteAdd(id);
        model.addAttribute("addsFromBE", service.getAdds());
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping("/admin/edit-page{id}")
    public String getUpdatepage(long id, Model model) {
        LOGGER.info("RequestMapping admin get Update Page : starts, for ID = " + id);
        model.addAttribute("add", getSpecificAdd(id));
        return "update";
    }

    @PostMapping("/admin/edit")
    public String updateAddForm(Adds add, Model model) {
        LOGGER.info("RequestMapping admin Update Add : starts, for ID = " + add.getAddId());
        updateAdd(add);
        return "redirect:/admin";
    }

    @PostMapping("/admin/sync")
    public String syncAdds(Model model) {
        LOGGER.info("manuall sync : started");
        LocalTime time = LocalTime.now();
        clientSort();
        model.addAttribute("lastSync", time);
        LOGGER.info("manually sync : ended");
        return "redirect:/admin";
    }

    private void clientSort() {
        List<Client> clients = clientDAO.findAll();
        for (Client c : clientDAO.findAll()) {
            List<Adds> coursesByCategory = coursesDAO.findByCategoryCategoryId(c.getPreferedCategory());
            jmsTemplate.convertAndSend(c.getCLIENT_QUEUE(), serializationToJson(coursesByCategory));
            LOGGER.info("SORTED CLIENT " + c.getClientName() + " with courses " + coursesByCategory);
        }

    }

    private void updateAdd(Adds addToUpdate) {
        coursesDAO.save(addToUpdate);
        LOGGER.info("Course updated : " + addToUpdate.toString());
    }

    private void deleteAdd(long id) {
        coursesDAO.deleteById(id);
        LOGGER.info("Course deleted");
    }

    private void persistAdd(Adds add) {
        LOGGER.info("PERSIST STARTED");
        coursesDAO.save(add);
        LOGGER.info("Course Persisted : " + add.toString());
    }

    private Adds getSpecificAdd(long id) {
        Adds add = new Adds();
        add.setAddId(coursesDAO.findById(id).get().getAddId());
        add.setAddName(coursesDAO.findById(id).get().getAddName());
        add.setAddDescription(coursesDAO.findById(id).get().getAddDescription());
        add.setAddPrice(coursesDAO.findById(id).get().getAddPrice());
        return add;
    }

    private String serializationToJson(List<Adds> adds) {
        LOGGER.info("serializationToJson : started");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(adds);

        if (!json.isEmpty()) {
            LOGGER.info("serializationToJson FINISHED : SUCCESS " + json);
            return json;
        } else {
            LOGGER.info("serializationToJson FINISHED : FAIL ");
            return "ERROR";
        }
    }

    //endpoint to Check Payloads
    @RequestMapping(value = "/endpoint/allAdds",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addsListEndPoint() {
        LOGGER.info("EndPoint All Adds : started");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String addsTojson = gson.toJson(service.getAdds());

        LOGGER.info("EndPoint All return : " + addsTojson);

        return addsTojson;
    }

}
