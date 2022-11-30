package com.ayotycoon.utils;

import com.ayotycoon.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pair <A,B>{
    private A key;
    private B value;


    public String toJSON(CellType type) {
        try {
            var val = Util.typeParser((String) this.value, type);
            if (type == CellType.STRING) val = "\"" + val + "\"";
            return "{\"key\":\""+key+"\",\"value\":"+val+"}";
        }catch (Exception e){
            return "{\"key\":\""+key+"\",\"value\":"+value+"}";
        }
    }
}
