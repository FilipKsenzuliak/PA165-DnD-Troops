/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.service.TroopService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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
    
    private Mapper mapper = new DozerBeanMapper();
    
    public TroopDAO getTroopDAO() {
        return troopDAO;
    }
    
    public void setTroopDAO(TroopDAO troopDAO) {
        this.troopDAO = troopDAO;
    }
    
    @Override
    @Transactional
    public List<TroopDTO> getAllTroops() {
        List<TroopDTO> troopsDTO = new ArrayList<TroopDTO>();
        for(Troop troop : troopDAO.getAllTroops()) {
            troopsDTO.add(mapper.map(troop, TroopDTO.class));
        }
        return troopsDTO;
    }

    @Override
    @Transactional
    public TroopDTO getTroopById(Long id) {
        Validate.isTrue(id > 0, "Invalid id. Id < 0!");
        TroopDTO troop = mapper.map(troopDAO.getTroop(id), TroopDTO.class);
        return troop;
    }

    @Override
    @Transactional
    public void updateTroop(TroopDTO troop) {
        Validate.notNull(troop, "Null argument.");
        Validate.isTrue(troop.getId() > 0, "Troop is not present in DB.");
        troopDAO.updateTroop(mapper.map(troop, Troop.class));
    }

    @Override
    @Transactional
    public void deleteTroop(TroopDTO troop) {
        Validate.notNull(troop, "Null argument.");
        Validate.isTrue(troop.getId() > 0, "Troop is not present in DB.");
        troopDAO.removeTroop(mapper.map(troop, Troop.class));
    }

    @Override
    @Transactional
    public void createTroop(TroopDTO troopDTO) {
        Validate.notNull(troopDTO, "Null argument.");
        Troop troop = mapper.map(troopDTO, Troop.class);
        troopDAO.createTroop(troop);
        troopDTO.setId(troop.getId());
    }

    @Override
    @Transactional
    public void updateTroops(List<TroopDTO> troops) {
        Validate.notNull(troops, "Null argument.");
        for(TroopDTO troop : troops) {
            troopDAO.updateTroop(mapper.map(troop, Troop.class));
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
