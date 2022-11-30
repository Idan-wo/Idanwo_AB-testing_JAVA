package com.ayotycoon.controllers;

import com.ayotycoon.daos.requests.CellParams;
import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.daos.responses.SuccessResponse;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.services.CellService;
import com.ayotycoon.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")

public class IndexController {
    private final CellService cellService;


    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public SuccessResponse ping() throws Exception {

        return new SuccessResponse("ok", SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }

}
