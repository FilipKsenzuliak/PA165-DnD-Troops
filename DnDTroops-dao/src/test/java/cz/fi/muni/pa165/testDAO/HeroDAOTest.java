/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.testDAO;


import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Race;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Jelínek
 */
@Transactional
@ContextConfiguration("file:src/main/resources/META-INF/applicationContext-dao-test.xml")
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class HeroDAOTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private HeroDAO heroDAO;
    
    private final Random randomizer = new Random();

    public HeroDAOTest() {
    }

    private Hero getDummyHero() {
        Hero h = new Hero();
        h.setName("Ozak" + randomizer.nextInt());
        h.setRace(Race.ELF);
        h.setRank(1000l);
        return h;
    }
    
    private Hero insertBasicHero() {
        Hero h = getDummyHero();
        heroDAO.createHero(h);
        return h;
    }
    
    @Test
    public void createHero() {
        Hero h = insertBasicHero();
        assertEquals(heroDAO.retrieveHeroById(h.getId()), h, "Hero is not correctly inserted");
    }
    
    @Test
    public void updateHero() {
        Hero h = insertBasicHero();
        h.setName("Odin");
        h.setRank(100000l);
        heroDAO.updateHero(h);
        Hero dbHero = heroDAO.retrieveHeroById(h.getId());
        assertEquals(dbHero.getName(), "Odin");
        assertEquals((long) dbHero.getRank(), 100000l);
    }
    
    @Test
    public void deleteHero() {
        Hero h = insertBasicHero();
        heroDAO.deleteHero(h);
        assertNull(heroDAO.retrieveHeroById(h.getId()));
    }
    
    @Test
    public void retrieveHeroById() {
        Hero h = insertBasicHero();
        Hero dbHero = heroDAO.retrieveHeroById(h.getId());
        assertNotNull(dbHero);
    }
    
    @Test
    public void retrieveHeroByName() {
        Hero h = getDummyHero();
        h.setName("Special named hero");
        heroDAO.createHero(h);
        Hero dbHero = heroDAO.retrieveHeroByName("Special named hero");
        assertNotNull(dbHero);
    }
    
    @Test
    public void retrieveAllHeroes() {
        List<Hero> allHeroes = heroDAO.retrieveAllHeroes();
        for (Hero oldHero : allHeroes) {
            heroDAO.deleteHero(oldHero);
        }
        
        allHeroes = heroDAO.retrieveAllHeroes();
        assertEquals(0, allHeroes.size());
        
        for (int i = 0; i < 10; i++) {
            insertBasicHero();
        }
        allHeroes = heroDAO.retrieveAllHeroes();
        assertEquals(10, allHeroes.size());
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void isThrowingDataAccessException() {
        Hero h = this.getDummyHero();
        h.setName("Same name");
        Hero h2= this.getDummyHero();
        h2.setName("Same name");
        
        this.heroDAO.createHero(h);
        this.heroDAO.createHero(h2);
    }
}