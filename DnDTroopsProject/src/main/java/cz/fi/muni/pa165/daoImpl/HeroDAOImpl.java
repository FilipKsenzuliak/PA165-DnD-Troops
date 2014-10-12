/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.daoImpl;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
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
    
    private EntityManager entityManager;
    
    public HeroDAOImpl(EntityManager em) {
        entityManager = em;
    }
    
    @Override
    public void createHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() != null || hero.getRace() == null || 
                hero.getAge() == null || hero.getRank() == null ||
                hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Create hero called with wrong param.");
        } 

        entityManager.persist(hero);
    }

    @Override
    public Hero getHero(Long id) throws IllegalArgumentException {
        if(id == null) {
            throw new IllegalArgumentException("getHero called with null.");
        }
        
        Hero result = entityManager.find(Hero.class,id);
        entityManager.detach(result);
        return result;
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() != null || hero.getRace() == null || 
                hero.getAge() == 0 || hero.getRank() == 0 ||
                hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Update hero called with wrong param.");
        }
        
        Hero find = entityManager.find(Hero.class, hero.getId());
        if(find == null) {
            throw new IllegalArgumentException("Hero is not present in db.");
        }
        
        entityManager.merge(hero);
    }

    @Override
    public void removeHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() == null) {
            throw new IllegalArgumentException("Remove hero called with wrong param");
        }
        Hero result = entityManager.find(Hero.class,hero.getId());
        entityManager.remove(result);
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroes;
        
        Query query = entityManager.createQuery("SELECT c FROM Customer c ORDER BY c.id");
        heroes = query.getResultList();
        entityManager.detach(heroes);
        return heroes;
    }

    @Override
    public List<Hero> findHeroByName(String Name) throws IllegalArgumentException {
        List<Hero> hero;
        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name ORDER BY c.id");
        //query.setParameter("name", name);
        hero = query.getResultList();
        entityManager.detach(hero);
        return hero;
    }
    
}
