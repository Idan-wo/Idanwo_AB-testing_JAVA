package com.ayotycoon.controllers;

import com.ayotycoon.daos.requests.CellParams;
import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.daos.responses.SuccessResponse;
import com.ayotycoon.services.CellService;
import com.ayotycoon.services.OrganisationService;
import com.ayotycoon.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/cells")
@CrossOrigin(origins = "*")

public class CellController {
    private final CellService cellService;



    @RequestMapping(value = "", method = RequestMethod.GET)
    public SuccessResponse getCells(GenericParams params, CellParams cellParams) throws Exception {
        var cells = cellService.getCells(params);
        if (cellParams.isRandom()) Util.cellValueRandomizer(cells);
        var data = Util.cellToConditionalPair(cells, cellParams.isPair());
        return new SuccessResponse(data, SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public SuccessResponse getCell(@PathVariable String key, CellParams cellParams) throws Exception {
        var cell = cellService.getCell(key).get();
        if (cellParams.isRandom()) Util.cellValueRandomizer(cell);
        var data = Util.cellToConditionalPair(cell, cellParams.isPair());
        return new SuccessResponse(data, SUCCESS_RESPONSES.GENERIC);
    }
// Should be authenticated
    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    public SuccessResponse patchCellValue(@PathVariable String key, @RequestBody String body) throws Exception {
        return new SuccessResponse(cellService.patchCellValue(key, body), SUCCESS_RESPONSES.GENERIC);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public SuccessResponse createCell(@RequestBody CreateCellBody body) throws Exception {
        return new SuccessResponse(cellService.createCell(body), SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }
}
