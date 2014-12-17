/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.entity.Mission;
import cz.fi.muni.pa165.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Hubac
 * @uco 396042
 */
@Repository("heroDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManagerFactory emf;

    public RoleDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void createRole(Role role) throws IllegalArgumentException {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        if (role.getId() != null) {
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
        if (id == null) {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role role = em.find(Role.class, id);
        em.getTransaction().commit();
        em.close();
        return role;
    }

    @Override
    public void updateRole(Role role) throws IllegalArgumentException {
        if (role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if (role.getId() == null) {
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
        if (role == null) {
            throw new IllegalArgumentException("Role can't be null.");
        }
        if (role.getId() == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role present = em.find(Role.class, role.getId());
        em.getTransaction().commit();

        if (present == null) {
            throw new IllegalArgumentException("Role is not present in DB.");
        } else {
            em.getTransaction().begin();
            em.remove(em.contains(role) ? role : em.merge(role));
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public List<Role> getAllRoles() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Role> roles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return roles;
    }
}
