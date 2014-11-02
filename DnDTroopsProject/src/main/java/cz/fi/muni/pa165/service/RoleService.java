/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.service;
import cz.fi.muni.pa165.dto.RoleDTO;
import java.util.List;
/**
 *
 * @author Dávid
 */
public interface RoleService {
    
    /**
     * Method to get all roles loaded in DB
     * @return List of roles
     */
    List<RoleDTO> getAllRoles();
    
    /**
     * Finds role by its Id
     * @param id
     * @return Role with Role.id = id
     */
    RoleDTO getRoleById(Long id);
    
    /**
     * Method updates the value for Role 'role' in DB
     * @param role 
     */
    void updateRole(RoleDTO role);
    
    /**
     * 
     * Method deletes Role 'role' from DB
     * @param role 
     */
    void deleteRole(RoleDTO role);
    
    /**
     * Method adds RoleDTO role to DB.
     * @param role 
     */
    void createRole(RoleDTO role);
    
    /**
     * Method deletes all roles present in DB.
     */
    void deleteAllRoles();
    
    /**
     * Method updates roles from list.
     * @param roles 
     */
    void updateRoles(List<RoleDTO> roles);
}
