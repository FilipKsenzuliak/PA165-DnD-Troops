/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Role;
import java.util.List;
/**
 *
 * @author Dávid Hubac
 * @uco 396042
 */
public interface RoleDAO {
    /**
     * Add new entry of given role to database
     * Role can't have ID set
     * 
     * @param role Role to be inserted
     * @throws IllegalArgumentException if argument is null or if ID is not null
     */
    public void createRole(Role role) throws IllegalArgumentException;
    
    /**
     * Find and return entity by its Id.
     * @param id
     * @return role Role where role.getId() == id
     * @throws IllegalArgumentException 
     */
    public Role getRoleById(Long id) throws IllegalArgumentException;
    
    /**
     * Edit existing entities.
     * @param role
     * @throws IllegalArgumentException 
     */
    public void updateRole(Role role) throws IllegalArgumentException;
    
    /**
     * Delete existing entity
     * @param role
     * @throws IllegalArgumentException 
     */
    public void deleteRole(Role role) throws IllegalArgumentException;
    
    /**
     * Finds and returns a collection of all roles stored in DB
     * If there are no roles in DB, returns empty List 
     * @return List of all roles in DB.
     */
    public List<Role> getAllRoles();
}
