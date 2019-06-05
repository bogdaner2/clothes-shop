package com.clothesshop.client.DAL;


import com.clothesshop.client.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Users, Integer> {
    void deleteByUsername(String username);
    Users findByUsername(String username);
}
