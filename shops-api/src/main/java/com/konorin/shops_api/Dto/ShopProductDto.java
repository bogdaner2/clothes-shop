package com.konorin.shops_api.Dto;
import java.util.UUID;

public class ShopProductDto {

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public ShopProductDto() {

    }


    public ShopProductDto(Integer productId, Integer shopId) {
        this.productId = productId;
        this.shopId = shopId;
    }

    private Integer productId;
    private Integer shopId;
}