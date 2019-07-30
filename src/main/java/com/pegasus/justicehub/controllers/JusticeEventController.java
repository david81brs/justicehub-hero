package com.pegasus.justicehub.controllers;

import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import com.pegasus.justicehub.services.JusticeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class JusticeEventController {

    @Autowired
    JusticeEventRepository jer;

    @Autowired
    JusticeEventService jes;

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<JusticeEvent> jevs = jer.findAll();
        String sumje =  jes.getSumPeopleAttended();
        mv.addObject("jevs", jevs);
        mv.addObject("sumje", sumje);
        return mv;
    }

    @GetMapping("/events")
    public Page<JusticeEvent> getJusticeEvents(Pageable pageable){
        return jer.findAll(pageable);
    }


    @GetMapping("/justiceevents/{id}")
    public String editEvent(@PathVariable("id") long id, ModelMap model){
        JusticeEvent je = jer.findById(id);
        model.addAttribute("JusticeEvent",je);
        return "eventsForm";
    }

    @PostMapping(value="/justiceevents")
    public  String postEvent(JusticeEvent je){
        jer.save(je);
        return "redirect:/index";
    }
    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id){
        JusticeEvent jev = jer.findById(id);
        jer.delete(jev);
        return "redirect:/index";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "adminer";
    }

    @GetMapping("/justiceevents")
    public String showForm(){
        return "events";
    }

}
