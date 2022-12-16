package com.ayotycoon.utils;

import com.ayotycoon.enums.CellType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;


class CellPairTest {

    @Test
    public void testThatToJSONGivesAccurateDefault(){
        var input = Map.of(
          new CellPair<>("key1", true), "{\"key\":\"key1\",\"value\":true}",
          new CellPair<>("key1", 1), "{\"key\":\"key1\",\"value\":1}",
          new CellPair<>("key1", "true"), "{\"key\":\"key1\",\"value\":\"true\"}"
        );

        for(var i: input.entrySet()){
            Assertions.assertEquals(i.getValue(), i.getKey().toJSON());
        }

    }
    @Test
    public void testThatToJSONGivesAccurateSpecified(){
        Assertions.assertEquals("{\"key\":\"key1\",\"value\":\"true\"}", new CellPair<>("key1", "true").toJSON(CellType.STRING));
        Assertions.assertEquals("{\"key\":\"key1\",\"value\":true}", new CellPair<>("key1", "true").toJSON(CellType.BOOLEAN));
        Assertions.assertEquals("{\"key\":\"key1\",\"value\":1}", new CellPair<>("key1", "1").toJSON(CellType.INT));
    }

}