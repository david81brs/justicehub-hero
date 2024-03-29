package com.pegasus.justicehub.controllers;



import com.pegasus.justicehub.models.JusticeEvent;
import com.pegasus.justicehub.models.JusticeEventResponse;
import com.pegasus.justicehub.repository.JusticeEventRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Api(value = "API REST Eventos de Justiça")
@RestController
@RequestMapping("v1")

public class JusticeApiController {


    private final JusticeEventRepository jer;

    public JusticeApiController(JusticeEventRepository jer) {
        this.jer = jer;
    }

    @ApiOperation(value = "Retorna oa lista de eventos de justiça")
    @RequestMapping(value = "/justiceevents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<JusticeEventResponse> listAllEvents() {
        List<JusticeEvent> je = jer.findAll();
        return ResponseEntity.ok(new JusticeEventResponse(je));
    }

    @ApiOperation(value="Atualiza um eventos de justiça")
    @PutMapping("/justiceevents")
    public ResponseEntity<?> updateJusticeEvent(@RequestBody JusticeEvent nje){
        JusticeEvent je = jer.findById(nje.getId());
        je.setPeopleAttended(nje.getPeopleAttended());
        je.setContacPerson(nje.getContacPerson());
        je.setEventEndDate(nje.getEventEndDate());
        je.setEventStartDate(nje.getEventStartDate());
        je.setEventTitle(nje.getEventTitle());
        je.setEventLocation(nje.getEventLocation());
        je.setInformer(nje.getInformer());
        jer.save(je);
        return ResponseEntity.ok("Resource Updated!");

    }

    @ApiOperation(value = "Busca um evento de justiça espefício")
    @GetMapping(value="/justiceevents/{id}", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public JusticeEvent getEvent(@PathVariable("id") long id){

        return jer.findById(id);
    }
}
