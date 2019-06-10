package com.konorin.shops_api.Repository;


import com.konorin.shops_api.Models.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
    Optional<Shop> findByIdAndIsDeletedFalse(int id);
    List<Shop> findByIsDeletedFalse();
}