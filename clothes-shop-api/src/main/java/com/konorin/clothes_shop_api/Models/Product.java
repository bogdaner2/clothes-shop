package com.konorin.clothes_shop_api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Brand brand;

    @Column
    private Integer price;

    @Column
    private String type;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    public Product(Brand brand, Integer price, String type) {
        this.brand = brand;
        this.price = price;
        this.type = type;
    }

    public Product() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void  setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}