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
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
public class TroopDAOImpl implements TroopDAO{

    public EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
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
        
        this.em.persist(troop);
    }

    @Override
    public Troop getTroopById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        return this.em.find(Troop.class, id);
    }

    @Override
    public void updateTroop(Troop troop) throws IllegalArgumentException {
        if(troop == null) {
            throw new IllegalArgumentException("Troop can't be null.");
        }
        if(troop.getId() == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        }        
        this.em.merge(troop);
    }

    @Override
    public void deleteTroop(Troop troop) throws IllegalArgumentException {
        if(troop == null) {
            throw new IllegalArgumentException("Troop can't be null.");
        }
        if(troop.getId() == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        } 
        this.em.remove(troop);
    }

    @Override
    public List<Troop> getAllTroops() {
        return this.em.createQuery("SELECT t FROM Troop t", Troop.class).getResultList();
    }
}
