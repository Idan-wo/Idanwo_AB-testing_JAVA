package com.ayotycoon.daos.requests;

import lombok.Data;

@Data
public class CreateOrgBody {

    private String adminUsername;
    private String adminPassword;

}
