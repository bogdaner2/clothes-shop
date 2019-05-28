package com.clothesshop.client.Models;

public class Product {
    private Integer id;
    private Brand brand;
    private Integer price;
    private String type;
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
}