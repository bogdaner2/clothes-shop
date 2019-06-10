package com.konorin.shops_api.Repository;

import com.konorin.shops_api.Models.ShopProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopProductRepository extends CrudRepository<ShopProduct, Integer> {
    Optional<ShopProduct> findByIdAndIsDeletedFalse(int id);
    List<ShopProduct> findByIsDeletedFalse();
}
