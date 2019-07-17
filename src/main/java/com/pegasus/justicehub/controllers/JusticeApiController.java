package com.pegasus.justicehub.controllers;


import com.fasterxml.jackson.annotation.JsonRootName;
import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.models.JusticeEventResponse;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("v1")

public class JusticeApiController {


    private final JusticeEventRepository jer;

    public JusticeApiController(JusticeEventRepository jer){
        this.jer = jer;
    }



//    @RequestMapping(value="/poke_events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    //@ResponseBody
//
//    public ResponseEntity<List<JusticeEvent>> listAllEvents(){
//        List<JusticeEvent> je = jer.findAll();
//        return new ResponseEntity<List<JusticeEvent>>(je, HttpStatus.OK);
//    }

    @RequestMapping(value="/poke_events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<JusticeEventResponse> listAllEvents(){
        List<JusticeEvent> je = jer.findAll();
        return ResponseEntity.ok(new JusticeEventResponse(je));
    }



//    @RequestMapping(value="/justice_events", method=RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
//    public ResponseEntity<List<JusticeEvent>> justicex(){
//        List<JusticeEvent> je = jer.findAll();
//        return new ResponseEntity<List<JusticeEvent>>(je, HttpStatus.OK);
//    }
}
