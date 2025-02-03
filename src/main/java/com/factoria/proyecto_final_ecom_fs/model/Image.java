package com.factoria.proyecto_final_ecom_fs.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;

    public Image(){

    }

    public Image(String name, String url){
        this.name=name;
        this.url=url;
    }
}