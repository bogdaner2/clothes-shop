package com.konorin.shops_api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Brand {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String history;
    @Column
    private Integer foundationYear;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

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

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void  setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
