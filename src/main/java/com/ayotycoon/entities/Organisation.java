package com.ayotycoon.entities;

import com.ayotycoon.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Organisation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    private String name;


    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime modifiedOn;


}




