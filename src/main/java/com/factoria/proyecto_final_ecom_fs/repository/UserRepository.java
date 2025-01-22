package com.factoria.proyecto_final_ecom_fs.repository;

import com.factoria.proyecto_final_ecom_fs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}

