package com.ayotycoon.utils;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.entities.Cell;

import com.ayotycoon.enums.CellType;
import com.ayotycoon.exceptions.CellException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {

    public static Object cellToConditionalPair(Cell cell, boolean toPair) {
        if (toPair) return cell.toPair();
        return cell;
    }

    public static Page cellToConditionalPair(Page<Cell> cells, boolean toPair) {
        if (!toPair) return cells;
        List<CellPair<String, Object>> cellsList = cells.getContent().stream().map((Cell cell) -> cell.toPair()).collect(Collectors.toList());
        return new PageImpl<CellPair<String, Object>>(cellsList, cells.getPageable(), cells.getTotalElements());
    }

    public static void cellValueRandomizer(Page<Cell> cells) {
        for (Cell cell : cells) cellValueRandomizer(cell);
    }

    public static void cellValueRandomizer(Cell cell) {
        if (cell.getOptions() == null || cell.getOptions().size() == 0) return;
        int chosenInt = randomInt(0, 100);

        double total = 0;

        for (CellOption pair : cell.getOptions()) {
            total += pair.getProbability();
            if (chosenInt <= total) {
                cell.setValue(pair.getValue());
                break;
            }
        }

    }

    public static int randomInt(int Min, int Max) {
        return (int) (Math.random() * (Max - Min)) + Min;
    }


    public static String generateRandomString(int n) {
        String STRS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int ch = (int) (STRS.length() * Math.random());
            s.append(STRS.charAt(ch));
        }
        return s.toString();
    }


    public static void validateCellOptions(List<CellOption> options) throws CellException.IncorrectCell {
        if (options == null || options.size() == 0) return;
        double total = 0;

        for (CellOption pair : options) {
            if (pair.getProbability() < 0 || pair.getProbability() > 100)
                throw new CellException.IncorrectCell("Pair " + pair.getValue() + " has invalid value of " + pair.getProbability() + "%");
            total += pair.getProbability();
        }
        if (total != 100) throw new CellException.IncorrectCell("Total values in options must be 100");

    }

    public static Object typeParser(String value, CellType type) {
        switch (type) {
            case INT:
                return Integer.parseInt(value);

            case BOOLEAN:
                return Boolean.parseBoolean(value);

        }

        return value;
    }

    public static CellType assumeType(String value) {
       if(value.equals("false") || value.equals("true"))return CellType.BOOLEAN;
        try {
            Integer.parseInt(value);
            return CellType.INT;
        } catch (Exception e) {
        }

        return CellType.STRING;
    }
    public static boolean isValidKey(String str)
    {
        if (str == null)return false;
        return Pattern.compile("^[A-Za-z]\\w{2,}$").matcher(str).matches();
    }
}
