package com.pegasus.justicehub.controllers;

import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class JusticeEventController {

    @Autowired
    JusticeEventRepository jer;

    @GetMapping("/")
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

    @GetMapping(value="/justiceevents")
    public String getEvent(){

        return "events";
    }
    @PostMapping(value="/justiceevents")
    public  String postEvent(JusticeEvent je){
        jer.save(je);
        return "redirect:/";
    }
}
