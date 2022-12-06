package com.ayotycoon.daos.responses;


import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;

@Data
public class SuccessResponse {
    Object data;
    Object extra = null;
    String message;
    Boolean success = true;


    public SuccessResponse(Page data, SUCCESS_RESPONSES message) {
        parseToPage(data);
    }

    public SuccessResponse(Object data, SUCCESS_RESPONSES message) {


        this.data = data;
        this.message = message.name();

        if (data != null && data instanceof String) {
            this.message = message.name();
        }
    }

    private void parseToPage(Page data) {
        this.data = data.getContent();
        var e = CONSTANTS.OM.convertValue(data.getPageable(), HashMap.class);
        e.put("totalElements", data.getTotalElements());
        e.put("totalPages", data.getTotalPages());
        e.put("number", data.getNumber());
        e.put("numberOfElements", data.getNumberOfElements());


        this.extra = e;
    }


}
