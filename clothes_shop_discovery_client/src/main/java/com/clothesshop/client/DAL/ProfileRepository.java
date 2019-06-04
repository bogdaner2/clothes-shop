package com.clothesshop.client.DAL;

import com.clothesshop.client.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile getByUsername(String username);
}
