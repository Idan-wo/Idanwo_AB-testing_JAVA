package com.ayotycoon.services;


import com.ayotycoon.daos.requests.AddUserToOrgBody;
import com.ayotycoon.daos.requests.CreateOrgBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.entities.Organisation;
import com.ayotycoon.entities.User;
import com.ayotycoon.enums.UserRole;
import com.ayotycoon.exceptions.UnauthorizedException;
import com.ayotycoon.exceptions.UserAlreadyExistsException;
import com.ayotycoon.repositories.OrganisationRepository;
import com.ayotycoon.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrgService {
    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;



    public Organisation createOrg(CreateOrgBody body) throws UserAlreadyExistsException {
        if(userRepository.findFirstByUsername(body.getAdminUsername()) != null) throw new  UserAlreadyExistsException();
        var entity = new Organisation();
        var org =  organisationRepository.save(entity);
        var user = new User();
        user.setOrganisation(org);
        user.setUsername(body.getAdminUsername());
        user.setPassword(passwordEncoder.encode(body.getAdminPassword()));
        user.setRole(UserRole.IS_ADMIN);
        userRepository.save(user);
        return org;
    }


    public User addUserToOrg(AddUserToOrgBody body) throws UnauthorizedException, UserAlreadyExistsException {
        if(userRepository.findFirstByUsername(body.getUsername()) != null) throw new UserAlreadyExistsException();
        if(!authService.getAuthUser().getRoles().contains(UserRole.IS_ADMIN)) throw new UnauthorizedException();

        var user = new User();
        user.setOrganisation(authService.getAuthOrg());
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user.setRoles(body.getRoles());
        return userRepository.save(user);

    }

    public Page<Organisation> getOrganisations(GenericParams params) {
        return organisationRepository.findAll(PageRequest.of(params.getPage(), params.getSize()));
    }

}
