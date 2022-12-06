package com.ayotycoon.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class ParsedToken {
    private String userId;
    private String orgId;

    public ParsedToken(Claims claims){
        this.userId = claims.getSubject();
        this.orgId = claims.getId();
    }
}
