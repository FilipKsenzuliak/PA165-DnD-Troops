/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Troop;
import java.util.List;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
public interface TroopDAO {
 
    /**
     * Add new troop into database. Id is automatically generated and
     * stored into id attribute.
     *
     * @param troop troop to be created.
     * @throws IllegalArgumentException when troop is null, troop already has id assigned
     */
    public void createTroop(Troop troop) throws IllegalArgumentException;
    
    /**
     * Return troop with given id.
     *
     * @param id primary key of troop.
     * @return troop with given id or null if such troop is not in database.
     * @throws IllegalArgumentException when given id is null.
     */
    public Troop getTroopById(Long id) throws IllegalArgumentException;
    
    /**
     * Updates troop in database.
     *
     * @param troop troop to be updated.
     * @throws IllegalArgumentException when troop is null, or has null id.
     */
    public void updateTroop(Troop troop) throws IllegalArgumentException;
    
    /**
     * Remove troop from database.
     *
     * @param troop troop to be removed from database.
     * @throws IllegalArgumentException when troop is null, or has null id.
     */
    public void deleteTroop(Troop troop) throws IllegalArgumentException;
    
    /**
     * Return list of all troops in the database.
     *
     * @return list of all troops.
     */
    public List<Troop> getAllTroops();
    
}
