package com.ayotycoon.controllers;

import com.ayotycoon.daos.requests.CreateOrganisationBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.daos.responses.SuccessResponse;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.services.OrganisationService;
import com.ayotycoon.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/organisation")
@CrossOrigin(origins = "*")

public class OrganisationController {
    private final OrganisationService OrganisationService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public SuccessResponse getOrganisations(GenericParams params) throws Exception {
        var data = OrganisationService.getOrganisations(params);
        return new SuccessResponse(data, SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public SuccessResponse getOrganisation(@PathVariable String key) throws Exception {


        return new SuccessResponse(null, SUCCESS_RESPONSES.GENERIC);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public SuccessResponse createOrganisation(@RequestBody CreateOrganisationBody body) throws Exception {
        return new SuccessResponse(OrganisationService.createOrganisation(body), SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }
}
