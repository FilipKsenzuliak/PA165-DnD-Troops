/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
 public class TroopDAOImpl implements TroopDAO{

@Autowired EntityManager entityManager; public TroopDAOImpl(EntityManagerFactory emf) { } public EntityManager getEntityManager() { return entityManager; }

public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; } @Override public void createTroop(Troop troop) throws IllegalArgumentException { if(troop == null || troop.getId() != null || troop.getName() == null || troop.getMission()== null || troop.getAmountOfMoney() == null) { throw new IllegalArgumentException("Create troop called with wrong param"); } entityManager.persist(troop); }

@Override public Troop getTroop(Long id) throws IllegalArgumentException { if(id == null) { throw new IllegalArgumentException("getTroop called with null"); } Troop troop = entityManager.find(Troop.class, id);

return troop; }

@Override public void updateTroop(Troop troop) throws IllegalArgumentException { if(troop == null || troop.getName() == null || troop.getMission()== null || troop.getAmountOfMoney()== null) { throw new IllegalArgumentException("Update troop called with wrong param"); } entityManager.merge(troop); }

@Override public void removeTroop(Troop troop) throws IllegalArgumentException { if(troop == null) { throw new IllegalArgumentException("Troop can't be null."); } if(troop.getId() == null) { throw new IllegalArgumentException("Troop is not present in DB."); } if (entityManager.contains(troop)){ entityManager.remove(troop); }else{ entityManager.remove(entityManager.merge(troop)); } }

@Override public List<Troop> getAllTroops() { List<Troop> troops = entityManager.createQuery("SELECT t FROM Troop t", Troop.class).getResultList(); return troops; }

@Override public List<Troop> findTroopByName(String name) throws IllegalArgumentException { List<Troop> troop; troop = entityManager.createQuery("SELECT t FROM Troop t WHERE t.name = :name").setParameter("name", name).getResultList();

return troop; } }