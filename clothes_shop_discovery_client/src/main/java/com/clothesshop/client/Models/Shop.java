package com.clothesshop.client.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Shop {
    public Shop(String name, String location, boolean open) {
        this.name = name;
        this.location = location;
        this.open = open;
    }

    public Shop() {}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    private Integer id;
    private String name;
    private String location;
    private boolean open;

}
