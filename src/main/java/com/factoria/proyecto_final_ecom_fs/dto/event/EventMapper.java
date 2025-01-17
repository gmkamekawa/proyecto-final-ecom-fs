package com.factoria.proyecto_final_ecom_fs.dto.event;

import com.factoria.proyecto_final_ecom_fs.model.Event;

public class EventMapper {
    public static Event dtoToEntity(EventRequest eventRequest) {
        return new Event(eventRequest.title(), eventRequest.description(), eventRequest.image_url(), eventRequest.location(), eventRequest.date(), eventRequest.is_available());
    }

    public static EventResponse entityToDTO(Event event) {
        return new EventResponse(event.getTitle(), event.getImage_url(), event.getLocation(), event.getDate(), event.getIs_available());
    }
}
