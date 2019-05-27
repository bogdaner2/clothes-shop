package com.clothesshop.client.DAL;


import com.clothesshop.client.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthService extends JpaRepository<Users,Integer> {
}
