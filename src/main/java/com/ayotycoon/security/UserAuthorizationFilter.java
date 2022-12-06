package com.ayotycoon.security;


import com.ayotycoon.services.CONSTANTS;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;



public class UserAuthorizationFilter extends BasicAuthenticationFilter {



    public UserAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);

    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) {


            String header = request.getHeader(CONSTANTS.userHeaderName);

            if (header == null) {
                chain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader(CONSTANTS.userHeaderName).substring(7);

        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(CONSTANTS.userKey.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();


            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

        }
        return null;
    }
}
