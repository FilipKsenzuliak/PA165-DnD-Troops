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
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip
 */
@Repository
public class HeroDAOImpl implements HeroDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void createHero(Hero hero) throws IllegalArgumentException {
        if(hero == null || hero.getId() != null || hero.getRace() == null || 
                hero.getAge() == 0 || hero.getLevel() == 0 ||
                hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Create hero called with wrong param.");
        } 

        entityManager.persist(hero);
    }

    @Override
    public Hero getHero(Long id) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeHero(Hero hero) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> getAllHeroes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> findHeroByName(String Name) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
