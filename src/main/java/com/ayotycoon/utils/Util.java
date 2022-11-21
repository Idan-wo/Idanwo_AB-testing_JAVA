package com.ayotycoon.utils;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.entities.Cell;
import javafx.util.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static Object cellToConditionalPair(Cell cell, boolean toPair) {
        if (toPair) return cell.toPair();
        return cell;
    }

    public static Page cellToConditionalPair(Page<Cell> cells, boolean toPair) {
        if (!toPair) return cells;
        List<Pair<String, Object>> cellsList = cells.getContent().stream().map((Cell cell) -> cell.toPair()).collect(Collectors.toList());
        return new PageImpl<Pair<String, Object>>(cellsList, cells.getPageable(), cells.getTotalElements());
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


    public static void validateCellOptions(List<CellOption> options) throws Exception {
        if (options == null || options.size() == 0) return;
        double total = 0;

        for (CellOption pair : options) {
            if (pair.getProbability() < 0 || pair.getProbability() > 100)
                throw new Exception("Pair " + pair.getValue() + " has invalid value of " + pair.getProbability()+"%");
            total += pair.getProbability();
        }
        if (total < 0 || total > 100) throw new Exception("Total values in options must be 100");

    }
}
