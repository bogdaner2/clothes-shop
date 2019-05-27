package com.clothesshop.client.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Brand {

    private Integer id;
    private String name;
    private String history;
    private Integer foundationYear;

    public Brand(String name, String history, Integer foundationYear) {
        this.name = name;
        this.history = history;
        this.foundationYear = foundationYear;
    }

    public Brand() {}

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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

}
