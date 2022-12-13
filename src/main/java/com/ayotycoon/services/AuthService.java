package com.ayotycoon.services;


import com.ayotycoon.entities.Organisation;
import com.ayotycoon.entities.User;
import com.ayotycoon.exceptions.OrgIdHeaderNotFoundException;
import com.ayotycoon.exceptions.UnauthorizedException;
import com.ayotycoon.repositories.OrganisationRepository;
import com.ayotycoon.repositories.UserRepository;
import com.ayotycoon.security.ParsedToken;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final OrganisationRepository organisationRepository;
    private final HttpServletRequest request;
    private final AppService appService;

    public User getAuthUser() throws UnauthorizedException {
        String id = getParsedToken().getUserId();
        User user = userRepository.findFirstById(id);
        return user;
    }

    public Organisation getAuthOrg() throws UnauthorizedException {
        String orgId = getParsedToken().getOrgId();
        return organisationRepository.findFirstById(orgId);
    }

    public ParsedToken getParsedToken() throws UnauthorizedException {
        try {
            return new ParsedToken((Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    public String getHeaderOrgId() throws OrgIdHeaderNotFoundException {
        String val = request.getHeader(appService.getOrgIdHeader());

        if (Strings.isNullOrEmpty(val)) throw new OrgIdHeaderNotFoundException(appService.getOrgIdHeader());
        return val;
    }
}
