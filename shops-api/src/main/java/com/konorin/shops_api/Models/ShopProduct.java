package com.konorin.shops_api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ShopProduct {
    public Integer getId() {
        return id;
    }

    public ShopProduct(Shop shop, Product product) {
        this.shop = shop;
        this.product = product;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Shop shop;

    @JoinColumn
    @ManyToOne
    private Product product;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    public ShopProduct() {

    }
}