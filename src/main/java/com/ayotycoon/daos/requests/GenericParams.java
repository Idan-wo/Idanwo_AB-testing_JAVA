package com.ayotycoon.daos.requests;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
public class GenericParams {

    private int page = 0;
    private int size = 10;


    public void setPage(String param) {
        if (param == null || param.equals("")) {
            return;
        }
        page = Integer.parseInt(param);

    }

    public void setSize(String param) {
        if (param == null || param.equals("")) {
            return;
        }
        size = Integer.parseInt(param);

    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;


    }
}
