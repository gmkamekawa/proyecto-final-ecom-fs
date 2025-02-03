package com.factoria.proyecto_final_ecom_fs.repository;

import com.factoria.proyecto_final_ecom_fs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    Set<User> findByIdIn(List<Integer> ids);
}

