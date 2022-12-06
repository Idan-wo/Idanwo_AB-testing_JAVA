package com.ayotycoon.security;



import com.ayotycoon.daos.responses.ErrorResponse;
import com.ayotycoon.daos.responses.SuccessResponse;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.entities.User;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    private AuthenticationManager authenticationManager;


    public UserAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/v1/organisation/login");
    }


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

            User applicationUser = new ObjectMapper().readValue(req.getInputStream(), User.class);
            log.info(req.getInputStream().toString());
            var authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(applicationUser.getUsername(),
                            applicationUser.getPassword())
            );
            return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Date exp = new Date(System.currentTimeMillis() + CONSTANTS.userExpirationTime);
        Key key = Keys.hmacShaKeyFor(CONSTANTS.userKey.getBytes());

        var user = ((CustomAuthUser) auth.getPrincipal());
        Claims claims = Jwts.claims().setSubject(user.getId());
        claims.setId(user.getOrgId());
        String token = Jwts.builder().setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact();

        res.addHeader("token", token);

        ObjectMapper objectMapper = new ObjectMapper();
        res.setStatus(HttpServletResponse.SC_OK);
        res.setHeader("content-type", "application/json");
        res.getWriter().write(objectMapper.writeValueAsString(new SuccessResponse(token, SUCCESS_RESPONSES.GENERIC)));

        res.getWriter().flush();
        res.getWriter().close();
        res.getWriter().close();


    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        logger.error(e.getLocalizedMessage(), e);
        response.setHeader("content-type", "application/json");
        response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorResponse(e.getMessage())));





    }
}
