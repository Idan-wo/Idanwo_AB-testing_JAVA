package com.ayotycoon.repositories;

import com.ayotycoon.entities.Cell;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface CellRepository extends MongoRepository<Cell,String> {



    Page<Cell> findBy(Pageable page);
    Page<Cell> findFirstByOrgId(String id, PageRequest pageRequest);
    Page<Cell> findAllByKeyIn(List<String> keys, Pageable page);
    Page<Cell> findAllByKeyInAndOrgId(List<String> keys, String id, Pageable page);
    Optional<Cell> findFirstByKey(String key);
    Optional<Cell> findFirstByKeyAndOrgId(String key, String id);


}
