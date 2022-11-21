package com.ayotycoon.daos.requests;

import com.ayotycoon.enums.CellType;
import lombok.Data;

import java.util.List;

@Data
public class CreateCellBody {
    private String key;
    private String value;
    private CellType type = CellType.STRING;
    private List<CellOption> options;

    public void setType(String str) {
        if(str == null) return;
        str = str.toLowerCase();
        if(str.startsWith("bool") || value.equals("true") || value.equals("false")){
            type = CellType.BOOLEAN;
            return;
        }
        if(str.startsWith("str")){
            type = CellType.STRING;
            return;
        }
        if(str.startsWith("int")){
            type = CellType.INT;
            return;
        }

    }
}
