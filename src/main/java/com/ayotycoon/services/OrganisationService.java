package com.ayotycoon.services;


import com.ayotycoon.daos.requests.CreateOrganisationBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.entities.Organisation;
import com.ayotycoon.entities.User;
import com.ayotycoon.enums.UserRole;
import com.ayotycoon.repositories.OrganisationRepository;
import com.ayotycoon.repositories.UserRepository;
import com.ayotycoon.utils.Util;
import com.ayotycoon.utils.WSManager;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public Organisation createOrganisation(CreateOrganisationBody body) throws Exception {
        if(userRepository.findFirstByUsername(body.getAdminUsername()) != null) throw new Exception("User already exists");
        var entity = new Organisation();
        var org =  organisationRepository.save(entity);

        var user = new User();
        user.setOrganisation(org);
        user.setUsername(body.getAdminUsername());
        user.setPassword(passwordEncoder.encode(body.getAdminPassword()));
        user.setRoles(UserRole.IS_ADMIN);
        userRepository.save(user);



        return org;

    }

    public Page<Organisation> getOrganisations(GenericParams params) {
        return organisationRepository.findAll(PageRequest.of(params.getPage(), params.getSize()));
    }

}
