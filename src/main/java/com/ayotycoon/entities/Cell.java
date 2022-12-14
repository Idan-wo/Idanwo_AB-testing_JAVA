package com.ayotycoon.entities;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.enums.CellType;
import com.ayotycoon.utils.CellPair;
import com.ayotycoon.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed
    private String key;
    private String value;
    private CellType type = CellType.STRING;
    private List<CellOption> options;
    private ObjectId orgId;
    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime modifiedOn;

    public CellPair<String, Object> toPair(){
        Object parsedValue = Util.typeParser(value, type);
        return new CellPair<>(key,parsedValue);
    }

    public void setOrgId(String orgId){

        this.orgId = new ObjectId(orgId);
    }


}




