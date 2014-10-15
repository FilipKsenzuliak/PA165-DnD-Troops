/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */

public class HeroDAOImpl implements HeroDAO{
    
    private EntityManagerFactory emf;
    
    public HeroDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void createHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() != null || hero.getRace() == null || 
                hero.getAge() == null || hero.getRank() == null ||
                hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Create hero called with wrong param.");
        } 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(hero);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Hero getHero(Long id) throws IllegalArgumentException {
        if(id == null) {
            throw new IllegalArgumentException("getHero called with null.");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Hero hero = em.find(Hero.class, id);
        em.getTransaction().commit();
        em.close();
        return hero;
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getRace() == null || 
                hero.getAge() == 0 || hero.getRank() == 0 ||
                hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Update hero called with wrong param.");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();   
        em.merge(hero);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() == null) {
            throw new IllegalArgumentException("Remove hero called with wrong param");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();   
        Hero present = em.find(Hero.class, hero.getId());
        em.getTransaction().commit();
        
        if(present == null) {
            throw new IllegalArgumentException("Car is not present in DB.");
        } else {
            em.getTransaction().begin();   
            em.remove(em.contains(hero) ? hero : em.merge(hero));
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public List<Hero> getAllHeroes() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Hero> heroes = em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return heroes;
    }

    @Override
    public List<Hero> findHeroByName(String name) throws IllegalArgumentException {
        List<Hero> hero;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        hero = em.createQuery("SELECT h FROM Hero h WHERE h.name = :name").setParameter("name", name).getResultList();
        //query.setParameter("name", name);
        em.getTransaction().commit();
        em.close();
        return hero;
    }
    
}
