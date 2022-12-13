package com.ayotycoon.config.redis;

import com.ayotycoon.entities.Cell;
import com.ayotycoon.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class RedisPubSubMessage {
    private String appId;
    private String cellKey;
    private String cellValue;
    private CellType cellType;
    private String orgId;


    public String getCellKeyWithOrgId(){
        return "%s|%s".formatted(orgId, cellKey);
    }


    @Override
    public String toString() {
        if(orgId == null)
        return "%s|%s|%s|%s".formatted(
                appId,
                cellKey,
                cellValue,
                cellType
        );
        else
            return "%s|%s|%s|%s|%s".formatted(
                    appId,
                    cellKey,
                    cellValue,
                    cellType,
                    orgId
            );
    }
    public static RedisPubSubMessage fromString(String s){
        String [] arr = s.split("\\|");
        return new RedisPubSubMessage(arr[0],arr[1],arr[2],CellType.valueOf(arr[3]), arr.length >=4 ? arr[4] : null);
    }
}
