package com.ayotycoon.security;


import com.ayotycoon.entities.User;
import com.ayotycoon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    public UserDetails loadUserByUsername(String username) {
        User applicationUser = userRepository.findFirstByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        return new CustomAuthUser(applicationUser.getUsername(), applicationUser.getPassword(),applicationUser.getId().toString(),applicationUser.getOrgId().toString(), applicationUser.isEnabled(),true,true,true, new ArrayList<>());
    }

}
