package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.event.EventMapper;
import com.factoria.proyecto_final_ecom_fs.dto.event.EventRequest;
import com.factoria.proyecto_final_ecom_fs.dto.event.EventResponse;
import com.factoria.proyecto_final_ecom_fs.exception.EmptyException;
import com.factoria.proyecto_final_ecom_fs.model.Event;
import com.factoria.proyecto_final_ecom_fs.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse saveEvent(EventRequest eventRequest) {
        Event newEvent = EventMapper.dtoToEntity(eventRequest);
        Event savedEvent = eventRepository.save(newEvent);
        return EventMapper.entityToDTO(savedEvent);
    }

    public List<EventResponse> getEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) throw new EmptyException();
        return events.stream().map(event -> EventMapper.entityToDTO(event)).toList();
    }
}
