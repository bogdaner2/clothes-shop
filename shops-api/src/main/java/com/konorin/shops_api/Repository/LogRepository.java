package com.konorin.shops_api.Repository;


import com.konorin.shops_api.Models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
}

