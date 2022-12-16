package com.ayotycoon.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void isValidKey() {
        Map<String, Boolean> map = Map.of(
                "", false,
                "Ke", false,
                "Ke,y", false,
                "1Key", false,

                "Key", true,
                "Key11", true,
                "Ke_y", true

        );
        for (String key : map.keySet())
            assertEquals(map.get(key), Util.isValidKey(key));
    }
}