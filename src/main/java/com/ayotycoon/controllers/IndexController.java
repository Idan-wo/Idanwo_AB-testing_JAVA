package com.ayotycoon.controllers;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.daos.requests.CellParams;
import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.daos.responses.SuccessResponse;
import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.enums.CellType;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CellService;
import com.ayotycoon.services.WSManager.WSManager;
import com.ayotycoon.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")

public class IndexController {
    private final AppService appService;
    private final WSManager wsManager;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public SuccessResponse ping() throws Exception {

        return new SuccessResponse(new PingStatus(appService.getId(), wsManager.getSessions()), SUCCESS_RESPONSES.SUCCESSFUL_CREATION);
    }

}

@AllArgsConstructor
@Data
 class PingStatus {
    private String id;
    private Map<String, Set<String>> sessions;
}