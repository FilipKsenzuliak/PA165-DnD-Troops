/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.entity.Troop;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
public class TroopDAOImpl implements TroopDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("testingSetup");
    
    public TroopDAOImpl() {
        
    }
        
    @Override
    public void createTroop(Troop troop) throws IllegalArgumentException {
        if(troop == null)
        {
            throw new IllegalArgumentException("Troop cannot be null.");
        }
        if(troop.getId() != null)
        {
            throw new IllegalArgumentException("Troop identifier is already set.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(troop);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Troop getTroopById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Troop troop = em.find(Troop.class, id);
        em.close();
        return troop;
    }

    @Override
    public void updateTroop(Troop troop) throws IllegalArgumentException {
        if(troop == null) {
            throw new IllegalArgumentException("Troop can't be null.");
        }
        if(troop.getId() == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        }    
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(troop);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteTroop(Troop troop) throws IllegalArgumentException {
        if(troop == null) {
            throw new IllegalArgumentException("Troop can't be null.");
        }
        if(troop.getId() == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        } 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(troop);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Troop> getAllTroops() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Troop> roles = em.createQuery("SELECT t FROM Troop t", Troop.class).getResultList();
        em.close();
        return  roles;
    }
}
