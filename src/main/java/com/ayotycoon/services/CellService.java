package com.ayotycoon.services;

import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.entities.Cell;
import com.ayotycoon.repositories.CellRepository;
import com.ayotycoon.utils.Util;
import com.ayotycoon.utils.WSManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static java.util.Arrays.stream;

@Slf4j
@Service
@AllArgsConstructor
public class CellService {
    private final CellRepository cellRepository;


    public Cell createCell(CreateCellBody body) throws Exception {
        if (isCellExist(body.getKey()).isPresent()) throw new Exception("Email already exists");
        var newsLetter = new Cell();
        newsLetter.setKey(body.getKey());
        newsLetter.setValue(body.getValue());
        newsLetter.setType(body.getType());
        newsLetter.setOptions(body.getOptions());
        Util.validateCellOptions(body.getOptions());
        return cellRepository.save(newsLetter);

    }

    public Optional<Cell> isCellExist(String key) {
        return cellRepository.findFirstByKey(key);
    }


    public Page<Cell> getCells(GenericParams params) {
        var pageRequest = PageRequest.of(params.getPage(), params.getSize());
        if(params.getKeys() != null)
            return cellRepository.findAllByKeyIn(params.getKeys(), pageRequest);
        return cellRepository.findBy(pageRequest);
    }

    public Optional<Cell> getCell(String key) {
        return cellRepository.findFirstByKey(key);
    }

    public Cell patchCellValue(String key, String value) throws Exception{
        var o = isCellExist(key);
        if (!o.isPresent()) throw new Exception("Cell doesnt exist");

        Cell cell = o.get();
        cell.setValue(value);
        WSManager.broadcastKeyChanges(key,cell);

        return cellRepository.save(cell);

    }
}
