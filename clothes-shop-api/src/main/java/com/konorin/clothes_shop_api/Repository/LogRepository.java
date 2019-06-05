package com.konorin.clothes_shop_api.Repository;

import com.konorin.clothes_shop_api.Models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
}

