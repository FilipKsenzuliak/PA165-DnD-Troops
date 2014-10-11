/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.MissionDAO;
import cz.fi.muni.pa165.entity.Mission;
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
 * @author Tomas Javorsky a.k.a. Tomus
 * uco: 324662
 */
public class MissionDAOImpl implements MissionDAO{

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void createMission(Mission mission) throws IllegalArgumentException {
        if(mission == null)
        {
            throw new IllegalArgumentException("Mission cannot be null.");
        }
        if(mission.getId() != null)
        {
            throw new IllegalArgumentException("Mission identifier is already set.");
        }
        
        this.em.persist(mission);
    }

    @Override
    public Mission getMissionById(Long id) throws IllegalArgumentException {
        if(id == null)
        {
            throw new IllegalArgumentException("Identifier should now be null.");
        }
        return this.em.find(Mission.class, id);
    }

    @Override
    public void updateMission(Mission mission) throws IllegalArgumentException {
        if(mission == null) {
            throw new IllegalArgumentException("Mission can't be null.");
        }
        if(mission.getId() == null) {
            throw new IllegalArgumentException("Mission is not present in DB.");
        }        
        this.em.merge(mission);
    }

    @Override
    public void deleteMission(Mission mission) throws IllegalArgumentException {
        if(mission == null) {
            throw new IllegalArgumentException("Mission can't be null.");
        }
        if(mission.getId() == null) {
            throw new IllegalArgumentException("Mission is not present in DB.");
        } 
        this.em.remove(mission);
    }

    @Override
    public List<Mission> getAllMissions() {
        return this.em.createQuery("SELECT r FROM Mission r", Mission.class).getResultList();   
    }
}
