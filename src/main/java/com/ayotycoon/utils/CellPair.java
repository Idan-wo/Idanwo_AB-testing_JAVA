package com.ayotycoon.utils;

import com.ayotycoon.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CellPair<A,B>{
    private A key;
    private B value;


    public String toJSON(CellType type) {
        try {

            var val = Util.typeParser((String) this.value, type);
            if (type == CellType.STRING) val = "\"" + val + "\"";
            return "[\""+key+"\","+val+"]";
        }catch (Exception e){
            return "[\""+key+"\",\""+value+"\"]";
        }
    }
    public String toJSON() {

        var type = CellType.STRING;
        if(value instanceof Integer)type = CellType.INT;
        else if(value instanceof Boolean)type = CellType.BOOLEAN;

        return toJSON(type);

    }
    public List<Object> getList(){
        return List.of(key,value);
    }
}
