package com.factoria.proyecto_final_ecom_fs.dto.user;

import com.factoria.proyecto_final_ecom_fs.model.User;

public class UserMapper {

    public static User dtoToEntity(UserDTORequest userDTORequest) {
        return new User(
                userDTORequest.name(),
                userDTORequest.surname(),
                userDTORequest.email(),
                userDTORequest.password()
        );
    }

    public static UserDTOResponse entityToDTO(User user) {
        return new UserDTOResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
