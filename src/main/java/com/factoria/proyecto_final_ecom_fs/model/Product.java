package com.factoria.proyecto_final_ecom_fs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
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

    public Product() {
    }

    public Product(String name, String description, float price, boolean feature, String url_image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.feature = feature;
        this.url_image = url_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
