/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.converter.TroopConverter;
import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.service.TroopService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrej
 */
public class TroopServiceImpl implements TroopService {

    @Autowired
    private TroopDAO troopDAO;
    
    public TroopDAO getTroopDAO() {
        return troopDAO;
    }
    
    public void setRoleDAO(TroopDAO troopDAO) {
        this.troopDAO = troopDAO;
    }
    
    @Override
    @Transactional
    public List<TroopDTO> getAllTroops() {
        List<TroopDTO> troopsDTO = new ArrayList<TroopDTO>();
        for(Troop troop : troopDAO.getAllTroops()) {
            troopsDTO.add(TroopConverter.TroopToTroopDTO(troop));
        }
        return troopsDTO;
    }

    @Override
    @Transactional
    public TroopDTO getTroopById(Long id) {
        Validate.isTrue(id > 0, "Invalid id. Id < 0!");
        TroopDTO troop = TroopConverter.TroopToTroopDTO(troopDAO.getTroop(id));
        return troop;
    }

    @Override
    @Transactional
    public void updateTroop(TroopDTO troop) {
        Validate.notNull(troop, "Null argument.");
        Validate.isTrue(troop.getId() > 0, "Troop is not present in DB.");
        troopDAO.updateTroop(TroopConverter.TroopDTOtoTroop(troop));
    }

    @Override
    @Transactional
    public void deleteTroop(TroopDTO troop) {
        Validate.notNull(troop, "Null argument.");
        Validate.isTrue(troop.getId() > 0, "Troop is not present in DB.");
        troopDAO.removeTroop(TroopConverter.TroopDTOtoTroop(troop));
    }

    @Override
    @Transactional
    public void createTroop(TroopDTO troopDTO) {
        Validate.notNull(troopDTO, "Null argument.");
        Troop troop = TroopConverter.TroopDTOtoTroop(troopDTO);
        troopDAO.createTroop(troop);
        troopDTO.setId(troop.getId());
    }

    @Override
    @Transactional
    public void updateTroops(List<TroopDTO> troops) {
        Validate.notNull(troops, "Null argument.");
        for(TroopDTO troop : troops) {
            troopDAO.updateTroop(TroopConverter.TroopDTOtoTroop(troop));
        }
    }

    @Override
    @Transactional
    public void deleteAllTroops() {
        List<Troop> troops = troopDAO.getAllTroops();
        for(Troop troop : troops) {
            troopDAO.removeTroop(troop);
        }
    }
    
}
