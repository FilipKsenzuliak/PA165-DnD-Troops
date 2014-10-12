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
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@Repository
public class HeroDAOImpl implements HeroDAO{
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("testingSetup");
    
    public HeroDAOImpl() {
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
        Hero result = em.find(Hero.class,id);
        em.detach(result);
        em.close();
        return result;
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() != null || hero.getRace() == null || 
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
        em.remove(hero);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Hero> getAllHeroes() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Hero> heroes = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList();
        em.close();
        return heroes;
    }

    @Override
    public List<Hero> findHeroByName(String Name) throws IllegalArgumentException {
        List<Hero> hero;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.name = :name ORDER BY c.id");
        //query.setParameter("name", name);
        hero = query.getResultList();
        em.detach(hero);
        em.close();
        return hero;
    }
    
}
