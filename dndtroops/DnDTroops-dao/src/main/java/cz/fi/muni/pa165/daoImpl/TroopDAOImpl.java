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
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrej Nemec
 * @UCO 396474
 */
@Repository("heroDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class TroopDAOImpl implements TroopDAO {

    @PersistenceContext
    private EntityManagerFactory emf;

    public TroopDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void createTroop(Troop troop) throws IllegalArgumentException {

        if (troop == null || troop.getId() != null || troop.getName() == null
                || troop.getMission() == null || troop.getAmountOfMoney() == null) {
            throw new IllegalArgumentException("Create troop called with wrong param");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(troop);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Troop getTroop(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("getTroop called with null");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Troop troop = em.find(Troop.class, id);
        em.getTransaction().commit();
        em.close();
        return troop;
    }

    @Override
    public void updateTroop(Troop troop) throws IllegalArgumentException {
        if (troop == null || troop.getName() == null
                || troop.getMission() == null || troop.getAmountOfMoney() == null) {
            throw new IllegalArgumentException("Update troop called with wrong param");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(troop);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeTroop(Troop troop) throws IllegalArgumentException {
        if (troop == null) {
            throw new IllegalArgumentException("Troop can't be null.");
        }
        if (troop.getId() == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Troop present = em.find(Troop.class, troop.getId());
        em.getTransaction().commit();

        if (present == null) {
            throw new IllegalArgumentException("Troop is not present in DB.");
        } else {
            em.getTransaction().begin();
            em.remove(em.contains(troop) ? troop : em.merge(troop));
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public List<Troop> getAllTroops() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Troop> troops = em.createQuery("SELECT t FROM Troop t", Troop.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return troops;
    }

    @Override
    public List<Troop> findTroopByName(String name) throws IllegalArgumentException {
        List<Troop> troop;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        troop = em.createQuery("SELECT t FROM Troop t WHERE t.name = :name").setParameter("name", name).getResultList();
        //query.setParameter("name", name);
        em.getTransaction().commit();
        em.close();
        return troop;
    }
}
