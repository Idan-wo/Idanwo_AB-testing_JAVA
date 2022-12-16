package com.ayotycoon.services;

import com.ayotycoon.daos.requests.CellOption;
import com.ayotycoon.daos.requests.CreateCellBody;

import com.ayotycoon.exceptions.CellException;
import com.ayotycoon.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class CellServiceTest {
    @Autowired
    private CellService cellService;

    @Test
    void createCell() throws UnauthorizedException, CellException.KeyAlreadyExists, CellException.IncorrectCell, CellException.InvalidCellKey {
     var input = new CreateCellBody();
            input.setKey("key1");
            input.setValue("value1");
            input.setType("string");
            input.setOptions(List.of(
                    new CellOption("prob1",3),
                    new CellOption("prob2",97)
            ));
           cellService.createCell(input);
    }

    @Test
    void createCellWithIncorrectProbabilities(){
       Exception exception = assertThrows(CellException.IncorrectCell.class, () -> {
            var input = new CreateCellBody();
            input.setKey("key2");
            input.setValue("value1");
            input.setType("string");
            input.setOptions(List.of(
                    new CellOption("prob1",3),
                    new CellOption("prob2",93)
            ));

            cellService.createCell(input);
        });
        System.out.println(exception.getMessage());



    }
}