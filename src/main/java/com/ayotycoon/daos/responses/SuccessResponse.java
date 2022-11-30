package com.ayotycoon.daos.responses;


import com.ayotycoon.daos.responses.enums.SUCCESS_RESPONSES;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;

@Data
public class SuccessResponse {
    public static HashMap<String, String> successResponsesHash = new HashMap<String, String>();
    Object data;
    Object extra;
    Object message;
    Boolean success = true;


    public SuccessResponse(Page data, SUCCESS_RESPONSES message) {
        this.message = successResponsesHash.get(message.name());
        parseToPage(data);
    }

    public SuccessResponse(Object data, SUCCESS_RESPONSES message) {


        this.data = data;
        this.message = successResponsesHash.get(message.name());

        if (data != null && data instanceof String) {
            this.message = message;
        }
    }

    private void parseToPage(Page data) {
        this.data = data.getContent();
        var e = new ObjectMapper().convertValue(data.getPageable(), HashMap.class);
        e.put("totalElements", data.getTotalElements());
        e.put("totalPages", data.getTotalPages());
        e.put("number", data.getNumber());
        e.put("numberOfElements", data.getNumberOfElements());


        this.extra = e;
    }


}
