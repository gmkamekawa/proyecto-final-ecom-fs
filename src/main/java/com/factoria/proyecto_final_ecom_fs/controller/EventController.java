package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.event.EventRequest;
import com.factoria.proyecto_final_ecom_fs.dto.event.EventResponse;
import com.factoria.proyecto_final_ecom_fs.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> saveEvent(@Valid @RequestBody EventRequest eventRequest) {
        return new ResponseEntity<>(eventService.saveEvent(eventRequest), HttpStatus.CREATED);
    }

    public ResponseEntity<List<EventResponse>> getEvents() {
        return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
    }
}
