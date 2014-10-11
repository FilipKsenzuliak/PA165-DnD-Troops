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
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Dávid
 * @uco 396042
 */
public class RoleDAOImpl implements RoleDAO{

    @PersistenceUnit
    public EntityManagerFactory emf;
    
    public RoleDAOImpl() {
        emf = Persistence.createEntityManagerFactory("Unit");
    }
    
    @Override
    public void createRole(Role role) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new IllegalArgumentException("Error when accessing DB.", e);
        }finally {
            em.close();
        }
    }

    @Override
    public Role getRoleById(Long id) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        Role role = null;
        try{
           role = em.find(Role.class, id);
        }catch(Exception e){
            throw new IllegalArgumentException("Error when accessing DB.", e);
        }finally {
            em.close();
            return role;
        }
    }

    @Override
    public void updateRole(Role role) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(role);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error when accessing DB.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteRole(Role role) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try {
            Role roleToDelete = em.find(Role.class, role.getId());
            em.getTransaction().begin();
            em.remove(role);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error when accessing DB.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Role> getAllRoles() {
        EntityManager em = emf.createEntityManager();
        List<Role> roles = new ArrayList<Role>();
        try {
            roles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error when accessing DB.", e);
        } finally {
            em.close();
            return roles;
        }
    }
}
