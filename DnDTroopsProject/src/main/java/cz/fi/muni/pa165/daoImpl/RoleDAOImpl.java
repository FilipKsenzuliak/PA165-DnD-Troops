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

/**
 *
 * @author D�vid
 * @uco 396042
 */
public class RoleDAOImpl implements RoleDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("testingSetup");
    
    public RoleDAOImpl() {
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Role getRoleById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role role = em.find(Role.class, id);
        em.close();
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(role);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteRole(Role role) throws IllegalArgumentException {
        if(role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if(role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        } 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(role);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Role> getAllRoles() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Role> roles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        em.close();
        return  roles;
    }
}
