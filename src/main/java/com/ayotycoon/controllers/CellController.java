package com.ayotycoon.controllers;

import com.ayotycoon.daos.requests.CellParams;
import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.daos.responses.generic.SuccessResponse;
import com.ayotycoon.services.CellService;
import com.ayotycoon.utils.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*")

public class CellController {
    private final CellService cellService;


    @RequestMapping(value = "/cells", method = RequestMethod.GET)
    public SuccessResponse getCells(GenericParams params,CellParams cellParams) throws Exception {

        return new SuccessResponse(Util.cellToConditionalPair(cellService.getCells(params), cellParams.isPair()), SUCCESS_RESPONSES.SUCCESSFUL_CREATION);

    }

    /**
     * Get a single Cell
     *
     * @param key
     * @param cellParams
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cells/{key}", method = RequestMethod.GET)
    public SuccessResponse getCell(@PathVariable String key, CellParams cellParams) throws Exception {
        var o = cellService.getCell(key);
        if(cellParams.isRandom())Util.cellValueRandomizer(o.get());
        return new SuccessResponse(Util.cellToConditionalPair(o.get(), cellParams.isPair()), SUCCESS_RESPONSES.GENERIC);

    }

    /**
     * Change the value of a single cell
     *
     * @param key
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cells/{key}", method = RequestMethod.PUT)
    public SuccessResponse patchCellValue(@PathVariable String key, @RequestBody String body) throws Exception {

        return new SuccessResponse(cellService.patchCellValue(key, body), SUCCESS_RESPONSES.GENERIC);

    }


    @RequestMapping(value = "/cells", method = RequestMethod.POST)
    public SuccessResponse createCell(@RequestBody CreateCellBody body) throws Exception {

        return new SuccessResponse(cellService.createCell(body), SUCCESS_RESPONSES.SUCCESSFUL_CREATION);

    }


}
