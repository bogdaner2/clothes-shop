package com.konorin.clothes_shop_api.Repository;

import com.konorin.clothes_shop_api.Models.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
    Optional<Brand> findByIdAndIsDeletedFalse(int id);
    List<Brand> findByIsDeletedFalse();
}