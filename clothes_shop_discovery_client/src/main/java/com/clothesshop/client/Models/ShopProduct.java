package com.clothesshop.client.Models;

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

    private Integer id;

    private Shop shop;

    private Product product;

    public ShopProduct() {

    }
}