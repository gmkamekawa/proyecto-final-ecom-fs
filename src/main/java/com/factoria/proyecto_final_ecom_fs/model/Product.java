package com.factoria.proyecto_final_ecom_fs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(length=400, nullable=false)
    private String description;

    @Column(nullable=false)
    private float price;

    @Column(nullable=false)
    private boolean feature;

    @Column(unique=true, nullable=false)
    private String url_image;
}
