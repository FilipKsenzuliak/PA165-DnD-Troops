/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.api.service;
import cz.fi.muni.pa165.api.dto.RoleDTO;
import java.util.List;
/**
 *
 * @author David Hubac
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
    RoleDTO updateRole(RoleDTO role);
    
    /**
     * 
     * Method deletes Role 'role' from DB
     * @param role 
     */
    Boolean deleteRole(RoleDTO role);
    
    /**
     * Method adds RoleDTO role to DB.
     * @param role 
     * @return role
     */
    RoleDTO createRole(RoleDTO role);
    
    /**
     * Method deletes all roles present in DB.
     */
    Boolean deleteAllRoles();
    
//    /**
//     * Method updates roles from list.
//     * @param roles 
//     */
//    RoleDTO updateRoles(List<RoleDTO> roles);
    
    RoleDTO retrieveRoleByName(String name);
    
}
