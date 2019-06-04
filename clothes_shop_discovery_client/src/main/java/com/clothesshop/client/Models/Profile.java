package com.clothesshop.client.Models;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

    public Profile(String username, String name, String email, String phone) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Profile() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}