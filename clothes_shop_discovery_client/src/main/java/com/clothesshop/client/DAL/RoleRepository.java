package com.clothesshop.client.DAL;


import com.clothesshop.client.Models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Authorities,Integer> {
}
