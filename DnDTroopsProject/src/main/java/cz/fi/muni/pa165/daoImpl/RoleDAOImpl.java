/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Dávid
 * @uco 396042
 */
public class RoleDAOImpl implements RoleDAO{

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void createRole(Role role) throws IllegalArgumentException {
        if(Role == null)
        {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        if(role.getId() != null)
        {
            throw new IllegalArgumentException("Role identifier is already set.");
        }
        
        this.em.persist(role);
    }

    @Override
    public Role getRoleById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        return this.em.find(Role.class, id);
    }

    @Override
    public void updateRole(Role role) throws IllegalArgumentException {
        if(role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if(role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        }        
        this.em.merge(role);
    }

    @Override
    public void deleteRole(Role role) throws IllegalArgumentException {
        if(role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if(role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        } 
        this.em.remove(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return this.em.createQuery("SELECT r FROM Role r", Role.class).getResultList();   
    }
}
