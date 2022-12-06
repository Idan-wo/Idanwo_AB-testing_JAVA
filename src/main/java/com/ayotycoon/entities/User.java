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
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;

    private String username;
    private String password;
    private boolean enabled = true;
    private ObjectId orgId;
    private Set<UserRole> roles = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime modifiedOn;

    public void setRoles(UserRole userRole){
        roles.add(userRole);
    }

    /*
    public Organisation getOrganisation() {
        return organisation;
    }
    */
//
//    public String getUsernameAndOrgId() {
//        return "%s+%s".formatted(username, organisationId.toString());
//    }
    public void setOrganisation(Organisation organisation) {
        this.orgId = organisation.getId();
    }
}




