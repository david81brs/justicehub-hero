package com.pegasus.justicehub.controllers;



import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.models.JusticeEventResponse;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("v1")

public class JusticeApiController {


    private final JusticeEventRepository jer;

    public JusticeApiController(JusticeEventRepository jer) {
        this.jer = jer;
    }

    @RequestMapping(value = "/poke_events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<JusticeEventResponse> listAllEvents() {
        List<JusticeEvent> je = jer.findAll();
        return ResponseEntity.ok(new JusticeEventResponse(je));
    }

    @PutMapping("/justiceevents/{id}")
    public String updateJusticeEvent(@PathVariable("id") long id, @RequestBody JusticeEvent nje){
        JusticeEvent je = jer.findById(id);
        je.setPeopleAttended(nje.getPeopleAttended());
        je.setContacPerson(nje.getContacPerson());
        je.setEventEndDate(nje.getEventEndDate());
        je.setEventStartDate(nje.getEventStartDate());
        je.setEventTitle(nje.getEventTitle());
        je.setEventLocation(nje.getEventLocation());
        je.setInformer(nje.getInformer());
        jer.save(je);
        return "API ok";

    }
    @GetMapping(value="/justiceevents/{id}", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public JusticeEvent getEvent(@PathVariable("id") long id){

        return jer.findById(id);
    }
}
