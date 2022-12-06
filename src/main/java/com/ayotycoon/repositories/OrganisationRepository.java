package com.ayotycoon.repositories;

import com.ayotycoon.entities.Organisation;
import com.ayotycoon.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface OrganisationRepository extends MongoRepository<Organisation,String> {

    Organisation findFirstById(String username);

}
