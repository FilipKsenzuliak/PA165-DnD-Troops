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
    private EntityManagerFactory emf;
    
    public RoleDAOImpl() {
        emf = Persistence.createEntityManagerFactory("myUnit");
        em = emf.createEntityManager();
    }
    
    @Override
    public void createRole(Role role) throws IllegalArgumentException {
        if(role == null)
        {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        if(role.getId() != null)
        {
            throw new IllegalArgumentException("Role identifier is already set.");
        }
        this.em.getTransaction().begin();
        this.em.persist(role);
        this.em.getTransaction().commit();
        this.em.close();
    }

    @Override
    public Role getRoleById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        this.em.getTransaction().begin();
        Role role = this.em.find(Role.class, id);
        this.em.close();
        return role;
    }

    @Override
    public void updateRole(Role role) throws IllegalArgumentException {
        if(role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if(role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        }        
        this.em.getTransaction().begin();
        this.em.merge(role);
        this.em.getTransaction().commit();
        this.em.close();
    }

    @Override
    public void deleteRole(Role role) throws IllegalArgumentException {
        if(role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if(role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        } 
        this.em.getTransaction().begin();
        this.em.remove(role);
        this.em.getTransaction().commit();
        this.em.close();
    }

    @Override
    public List<Role> getAllRoles() {
        this.em.getTransaction().begin();
        List<Role> roles = this.em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        this.em.close();
        return  roles;
    }
}
