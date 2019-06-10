package com.konorin.shops_api.Repository;


import com.konorin.shops_api.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByIdAndIsDeletedFalse(int id);
    List<Product> findByIsDeletedFalse();
}
