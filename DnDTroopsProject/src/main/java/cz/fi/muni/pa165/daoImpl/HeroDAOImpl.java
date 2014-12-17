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
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@Repository("heroDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class HeroDAOImpl implements HeroDAO {

    @PersistenceContext
    private EntityManager em;

    public HeroDAOImpl() {

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createHero(Hero hero) throws IllegalArgumentException {
        if (hero == null || hero.getId() != null || hero.getRace() == null
                || hero.getAge() == null || hero.getRank() == null
                || hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Create hero called with wrong param.");
        }
        em.persist(hero);
    }

    @Override
    public Hero getHeroById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("getHero called with null.");
        }
        Hero hero = em.find(Hero.class, id);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) throws IllegalArgumentException {
        if (hero == null || hero.getRace() == null
                || hero.getAge() == 0 || hero.getRank() == 0
                || hero.getRole() == null || hero.getTroop() == null) {
            throw new IllegalArgumentException("Update hero called with wrong param.");
        }

        em.merge(hero);
    }

    @Override
    public void deleteHero(Hero hero) throws IllegalArgumentException {
        if (hero == null || hero.getId() == null) {
            throw new IllegalArgumentException("Remove hero called with wrong param");
        }
        if (em.contains(hero)) {
            em.remove(hero);
        } else {
            em.remove(em.merge(hero));
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
        return heroes;
    }

    @Override
    public List<Hero> findHeroByName(String name) throws IllegalArgumentException {
        List<Hero> hero;
        hero = em.createQuery("SELECT h FROM Hero h WHERE h.name = :name").setParameter("name", name).getResultList();
        return hero;
    }

}
