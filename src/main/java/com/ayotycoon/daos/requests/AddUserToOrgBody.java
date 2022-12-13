package com.ayotycoon.daos.requests;

import com.ayotycoon.enums.UserRole;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class AddUserToOrgBody {

    private String username;
    private String password;
    private Set<UserRole> roles = new HashSet<>(){{addAll(List.of(UserRole.CAN_CREATE,UserRole.CAN_DELETE,UserRole.CAN_EDIT));}};

}
