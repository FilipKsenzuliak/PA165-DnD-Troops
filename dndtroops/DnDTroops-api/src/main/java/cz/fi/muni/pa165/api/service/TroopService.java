/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.service;

import cz.fi.muni.pa165.api.dto.TroopDTO;
import java.util.List;

/**
 *
 * @author Andrej
 */
public interface TroopService {
    /**
     * Method to get all troops loaded in DB
     * @return List of troops
     */
    List<TroopDTO> getAllTroops();
    
    /**
     * Finds troop by its Id
     * @param id
     * @return Troop with Troop.id = id
     */
    TroopDTO getTroopById(Long id);
    
    /**
     * Method updates the value for Troop 'troop' in DB
     * @param troop 
     */
    void updateTroop(TroopDTO troop);
    
    /**
     * 
     * Method deletes Troop 'troop' from DB
     * @param troop 
     */
    void deleteTroop(TroopDTO troop);
    
    /**
     * Method adds TroopDTO role to DB.
     * @param troop 
     */
    void createTroop(TroopDTO troop);
    
    /**
     * Method deletes all troops present in DB.
     */
    void deleteAllTroops();
    
    /**
     * Method updates troops from list.
     * @param troops 
     */
    void updateTroops(List<TroopDTO> troops);
}
