/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.serviceImpl;

import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.api.dto.RoleDTO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.api.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private RoleDAO roleDAO;

    public RoleServiceImpl() {
    }

    public RoleServiceImpl(Mapper mapper, RoleDAO roleDao) {
        this.mapper = mapper;
        this.roleDAO = roleDao;
    }

    private RoleDTO mapDTO(Role role) {
        return mapper == null || role == null ? null : mapper.map(role, RoleDTO.class);
    }

    private Role mapEntity(RoleDTO roleDTO) {
        return mapper == null || roleDTO == null ? null : mapper.map(roleDTO, Role.class);
    }

    @Override
    @Transactional
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
        for (Role role : roleDAO.getAllRoles()) {
            rolesDTO.add(mapper.map(role, RoleDTO.class));
        }
        return rolesDTO;
    }

    @Override
    @Transactional
    public RoleDTO getRoleById(Long id) {
        Validate.isTrue(id > 0, "Invalid id. Id < 0!");
        Validate.isTrue(id != null, "Id is null.");
        RoleDTO role = mapper.map(roleDAO.getRoleById(id), RoleDTO.class);
        return role;
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RoleDTO role) {
        Validate.notNull(role, "Null argument.");
        roleDAO.updateRole(mapper.map(role, Role.class));
        return role;
    }

    @Override
    @Transactional
    public Boolean deleteRole(RoleDTO role) {
        Validate.notNull(role, "Null argument.");
        roleDAO.deleteRole(mapper.map(role, Role.class));
        return true;
    }

    @Override
    public RoleDTO createRole(RoleDTO role) {
        return mapDTO(roleDAO.createRole(mapEntity(role)));
    }

//    @Override
//    @Transactional
//    public List<RoleDTO> updateRoles(List<RoleDTO> roles) {
//        Validate.notNull(roles, "Null argument.");
//        for (RoleDTO role : roles) {
//            roleDAO.updateRole(mapper.map(role, Role.class));
//        }
//        return roles;
//    }

    @Override
    @Transactional
    public Boolean deleteAllRoles() {
        List<Role> roles = roleDAO.getAllRoles();
        for (Role role : roles) {
            roleDAO.deleteRole(role);
        }
        return true;
    }
    
    @Override
    public RoleDTO retrieveRoleByName(String name) {
        return mapDTO(roleDAO.retrieveRoleByName(name));
    }
}
