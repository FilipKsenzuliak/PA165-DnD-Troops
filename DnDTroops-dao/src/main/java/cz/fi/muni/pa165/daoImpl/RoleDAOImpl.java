/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.entity.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Martin Peška
 * 
 * Class RoleDAOImpl implements RoleDAO interface
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager em;
    
    public RoleDAOImpl () {
    }

    @Override
    public Role createRole (Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public Role updateRole (Role role) {
        em.merge(role);
        return role;
    }

    @Override
    public Boolean deleteRole (Role role) {
        Role deleteRole = em.find(Role.class, role.getId());
        
        try {       
            em.remove(deleteRole);    
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Role> retrieveAllRoles () {
        List<Role> allRoles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        return allRoles;
    }

    @Override
    public Role retrieveRoleById (long id) {
        Role roleById = em.find(Role.class, id);
        return roleById;
    }

    @Override
    public Role retrieveRoleByName (String name) {
        try {
            Role roleByName = em.createQuery("SELECT r FROM Role r WHERE name=:name", Role.class).setParameter("name", name).getSingleResult();
            return roleByName;
        } catch (NoResultException ex) {
            return null;
        }
    }

}
