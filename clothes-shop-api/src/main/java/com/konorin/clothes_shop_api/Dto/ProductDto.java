package com.konorin.clothes_shop_api.Dto;
import java.util.UUID;

public class ProductDto {

    private UUID id;
    private Integer brandId;
    private Integer price;
    private String type;

    public ProductDto(UUID id, Integer brandId, Integer price, String type) {
        this.id = id;
        this.brandId = brandId;
        this.price = price;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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