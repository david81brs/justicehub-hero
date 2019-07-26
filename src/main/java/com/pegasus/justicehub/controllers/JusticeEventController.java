package com.pegasus.justicehub.controllers;

import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class JusticeEventController {

    @Autowired
    JusticeEventRepository jer;

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<JusticeEvent> jevs = jer.findAll();
        mv.addObject("jevs", jevs);
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

    @GetMapping(value="/justiceevents")
    public String getEvent(){
git
        return "events";
    }


    // PUT Mapping

    @PutMapping("/justiceevents")
    public String putEvent(@PathVariable("id") long id, @RequestBody JusticeEvent nje){
        JusticeEvent je = jer.findById(id);
        System.out.println("Put response");
        jer.save(je);
        return "ok";
    }

    @PostMapping(value="/justiceevents")
    public  String postEvent(JusticeEvent je){
        jer.save(je);
        return "redirect:/";
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


}
