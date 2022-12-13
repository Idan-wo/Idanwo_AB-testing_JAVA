package com.ayotycoon.services;

import com.ayotycoon.daos.requests.CreateCellBody;
import com.ayotycoon.daos.requests.GenericParams;
import com.ayotycoon.entities.Cell;
import com.ayotycoon.exceptions.CellKeyAlreadyExistsException;
import com.ayotycoon.exceptions.OrgIdHeaderNotFoundException;
import com.ayotycoon.exceptions.UnauthorizedException;
import com.ayotycoon.repositories.CellRepository;
import com.ayotycoon.security.ParsedToken;
import com.ayotycoon.utils.Util;
import com.ayotycoon.services.WSManager.WSManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class CellService {
    private final CellRepository cellRepository;
    private final AuthService authService;
    private final AppService appService;
    private final WSManager wsManager;


    public Cell createCell(CreateCellBody body) throws CellKeyAlreadyExistsException, UnauthorizedException, Exception {
        ParsedToken parsed = null;
        if(appService.isOrgMode()) {
            parsed = authService.getParsedToken();
            if (cellRepository.findFirstByKeyAndOrgId(body.getKey(), parsed.getOrgId()).isPresent()) throw new CellKeyAlreadyExistsException();
        }else{
            if (cellRepository.findFirstByKey(body.getKey()).isPresent()) throw new CellKeyAlreadyExistsException();
        }
        Cell cell = new Cell();
        cell.setKey(body.getKey());
        cell.setValue(body.getValue());
        cell.setType(body.getType());
        cell.setOptions(body.getOptions());
        if(appService.isOrgMode()) cell.setOrgId(parsed.getOrgId());
        Util.validateCellOptions(body.getOptions());
        return cellRepository.save(cell);

    }




    public Page<Cell> getCells(GenericParams params) throws UnauthorizedException, OrgIdHeaderNotFoundException {
        var pageRequest = PageRequest.of(params.getPage(), params.getSize());
        if(appService.isOrgMode()) {
            if (params.getKeys() != null)return cellRepository.findAllByKeyInAndOrgId(params.getKeys(), authService.getHeaderOrgId(), pageRequest);
            return cellRepository.findFirstByOrgId(authService.getHeaderOrgId(), pageRequest);
        }else{
            if (params.getKeys() != null)return cellRepository.findAllByKeyIn(params.getKeys(), pageRequest);
            return cellRepository.findBy(pageRequest);
        }
    }

    public Optional<Cell> getCell(String key) throws UnauthorizedException, OrgIdHeaderNotFoundException {
        if(appService.isOrgMode()) {
            return cellRepository.findFirstByKeyAndOrgId(key, authService.getHeaderOrgId());
        }else {
            return cellRepository.findFirstByKey(key);
        }
    }

    public Cell patchCellValue(String key, String value) throws Exception{
        ParsedToken parsed = null;
        Optional<Cell> o = null;
        if(appService.isOrgMode()) {
            parsed = authService.getParsedToken();
            o = cellRepository.findFirstByKeyAndOrgId(key, parsed.getOrgId());
        }
        else o = cellRepository.findFirstByKey(key);

        if (!o.isPresent()) throw new Exception("Cell doesnt exist");

        Cell cell = o.get();
        cell.setValue(value);
        wsManager.broadcastKeyChanges(key,cell);
        if (appService.isOrgMode()) cell.setOrgId(parsed.getOrgId());
        return cellRepository.save(cell);

    }
}
