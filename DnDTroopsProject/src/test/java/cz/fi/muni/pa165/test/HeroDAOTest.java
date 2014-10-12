/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.daoImpl.HeroDAOImpl;
import cz.fi.muni.pa165.entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author Dávid Hubaè
 * @uco 396042
 */
@ContextConfiguration(classes=DaoContext.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class HeroDAOTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManagerFactory emf;
    
    private HeroDAOImpl impl;
    private EntityManager em;
    
    public void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        impl = new HeroDAOImpl();
        impl.setEntityManager(em);
        
        Hero hero = new Hero();
        hero.setAge(35);
        hero.setLevel(20);
        hero.setName("Andrej");
        hero.setRace(Race.DWARF);
        
        Role role = new Role();
        role.setName("Wizard");
        role.setDescription("This is setup description.");
        role.setRoleLevel(4);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        hero.setRole(roles);
        
        Troop troop = new Troop();
        troop.setAmountOfMoney(100000);
        
        Mission mission = new Mission();
        mission.setName("Ryan.");
        mission.setObjective("Save private Ryan.");
        mission.setReward(10000);
        
        troop.setMission(mission);
        hero.setTroop(troop);
        
        em.persist(hero);
        em.getTransaction().commit();
    }
    
   /* @After
    public void restore() {
        em.createQuery("DELETE FROM Hero").executeUpdate();
        em.close();
   }*/
    
    @Test
    public void createTest(){
        Hero hero = new Hero();
        hero.setAge(35);
        hero.setLevel(20);
        hero.setName("Filip");
        hero.setRace(Race.ELF);
        
        Role role = new Role();
        role.setName("Rogue");
        role.setDescription("This is createTest description.");
        role.setRoleLevel(10);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        hero.setRole(roles);
        
        Troop troop = new Troop();
        troop.setAmountOfMoney(100);
        
        Mission mission = new Mission();
        mission.setName("Code.");
        mission.setObjective("Write some code.");
        mission.setReward(10);
        
        troop.setMission(mission);
        hero.setTroop(troop);
        
        try {
            setup();
            em.createQuery("DELETE FROM Hero").executeUpdate();
            List<Hero> heroes = em.createQuery("SELECT h FROM Hero h").getResultList();
            assertEquals(0, heroes.size());

            impl.createHero(hero);
            heroes = em.createQuery("SELECT h FROM Hero h").getResultList();
            assertNotNull(heroes);
            assertEquals(1, heroes.size());
            Hero heroGet = heroes.get(0);
            assertNotNull(heroGet);
            assertEquals("Filip", heroGet.getName());
            assertEquals("Rogue", heroGet.getRole().get(0).getName());
        } finally {
            em.close();
        }
    }
    
    @Test
    public void createNonNullId() {
        
    }
    
    @Test
    public void updateTest() {
        Hero hero = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0);
        
        hero.setAge(10);
    }
}
