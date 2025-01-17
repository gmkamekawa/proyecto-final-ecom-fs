package com.factoria.proyecto_final_ecom_fs.dto.event;

import java.time.LocalDateTime;

public record EventResponse(
        String title,
        String image_url,
        String location,
        LocalDateTime date,
        Boolean is_available
) {
}
