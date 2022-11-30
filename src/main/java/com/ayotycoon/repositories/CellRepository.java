package com.ayotycoon.repositories;

import com.ayotycoon.entities.Cell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface CellRepository extends MongoRepository<Cell,String> {



    Page<Cell> findBy(Pageable page);
    Page<Cell> findAllByKeyIn(List<String> keys, Pageable page);

    Optional<Cell> findFirstByKey(String key);
}
