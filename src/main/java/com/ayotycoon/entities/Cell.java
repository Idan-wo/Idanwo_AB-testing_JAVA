package com.ayotycoon.entities;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.enums.CellType;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cell {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String key;
    private String value;
    private CellType type = CellType.STRING;
    private List<CellOption> options;

    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime modifiedOn;

    public Pair<String, Object> toPair(){
        Object parsedValue = value;
        switch(type){
            case INT:
                parsedValue =  Integer.parseInt(value);
                break;
            case BOOLEAN:
                parsedValue =  Boolean.parseBoolean(value);
                break;
        }
        return new Pair<>(key,parsedValue);
    }


}




