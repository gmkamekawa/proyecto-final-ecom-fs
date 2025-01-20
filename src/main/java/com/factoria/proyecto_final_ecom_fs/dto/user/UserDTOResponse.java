package com.factoria.proyecto_final_ecom_fs.dto.user;

public record UserDTOResponse(
        int id,
        String name,
        String surname,
        String email,
        String password
) {

}
