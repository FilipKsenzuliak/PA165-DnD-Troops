/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.converter.RoleConverter;
import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Dávid
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDAO roleDAO;
    
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }
    
    public void serRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    
    @Override
    @Transactional
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
        for(Role role : roleDAO.getAllRoles()) {
            rolesDTO.add(RoleConverter.RoleToRoleDTO(role));
        }
        return rolesDTO;
    }
    
    @Override
    @Transactional
    public RoleDTO getRoleById(Long id) {
        Validate.isTrue(id > 0, "Invalid id. Id < 0!");
        RoleDTO role = RoleConverter.RoleToRoleDTO(roleDAO.getRoleById(id));
        return role;
    }
    
    @Override
    @Transactional
    public void updateRole(RoleDTO role) {
        Validate.notNull(role, "Null argument.");
        Validate.isTrue(role.getId() > 0, "Role is not present in DB.");
        roleDAO.updateRole(RoleConverter.RoleDTOtoRole(role));
    }
    
    @Override
    @Transactional
    public void deleteRole(RoleDTO role) {
        Validate.notNull(role, "Null argument.");
        Validate.isTrue(role.getId() > 0, "Role is not present in DB.");
        roleDAO.deleteRole(RoleConverter.RoleDTOtoRole(role));
    }
    
    @Override
    @Transactional
    public void createRole(RoleDTO roledto) {
        Validate.notNull(roledto, "Null argument.");
        Role role = RoleConverter.RoleDTOtoRole(roledto);
        roleDAO.createRole(role);
        roledto.setId(role.getId());
    }
    
    @Override
    @Transactional
    public void updateRoles(List<RoleDTO> roles) {
        Validate.notNull(roles, "Null argument.");
        for(RoleDTO role : roles) {
            roleDAO.updateRole(RoleConverter.RoleDTOtoRole(role));
        }
    }
    
    @Override
    @Transactional
    public void deleteAllRoles() {
        List<Role> roles = roleDAO.getAllRoles();
        for(Role role : roles) {
            roleDAO.deleteRole(role);
        }
    }
}
