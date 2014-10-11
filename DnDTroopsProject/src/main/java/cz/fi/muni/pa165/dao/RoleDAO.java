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
     * 
     * 
     */
    public void createRole(Role role) throws IllegalArgumentException;
    
    public Role getRoleById(Long id) throws IllegalArgumentException;
    
    public void updateRole(Role role) throws IllegalArgumentException;
    
    public void deleteRole(Role role) throws IllegalArgumentException;
    
    public List<Role> getAllRoles();
}
