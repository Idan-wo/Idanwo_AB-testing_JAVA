package com.ayotycoon.repositories;

import com.ayotycoon.entities.Cell;
import com.ayotycoon.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String> {






    User findFirstByUsername(String username);
    User findFirstById(String id);
}
