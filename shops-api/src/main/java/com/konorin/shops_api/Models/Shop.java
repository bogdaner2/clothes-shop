package com.konorin.shops_api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Shop {
    public Shop(String name, String location, boolean isOpen) {
        this.name = name;
        this.location = location;
        this.isOpen = isOpen;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private boolean isOpen;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;


    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void  setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
