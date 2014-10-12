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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Dávid Hubaè
 * @uco 396042
 */
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes=DaoContext.class)
public class HeroDAOTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private HeroDAOImpl impl;
    private EntityManager em;
    
    public HeroDAOTest() {
        
    }
    
    @Before
    public void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        impl = new HeroDAOImpl();
        
        Hero hero = new Hero();
        hero.setAge(35L);
        hero.setRank(20L);
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
        hero.setAge(35L);
        hero.setRank(20L);
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
        int count = em.createQuery("SELECT h FROM Hero h").getResultList().size();
        
        Hero hero = new Hero();
        hero.setAge(35L);
        hero.setRank(20L);
        hero.setName("Filip");
        hero.setRace(Race.ELF);
        hero.setId(777L);
        
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
        
        impl.createHero(hero);
        int count2 = em.createQuery("SELECT h FROM Hero h").getResultList().size();
        assertEquals(count, count2);
    }
    
    @Test
    public void updateTest() {
        Hero hero = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0);
        Long age = hero.getAge();
        hero.setAge(10L);
        impl.updateHero(hero);
        hero = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0);
        assertNotEquals(age, hero.getAge());
        
        hero.setId(null);
        impl.updateHero(hero);
        Long id = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0).getId();
        assertNotEquals(null, id);
    }
    
    @Test
    public void deleteTest() {
        int count = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().size();
        Hero hero = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0);
        
        impl.removeHero(hero);
        int count2 = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().size();
        assertNotEquals(count, count2);
    }
    
    @Test
    public void getByIdTest() {
        Hero hero = em.createQuery("SELECT r FROM Role r", Hero.class).getResultList().get(0);
        Long id = hero.getId();
        Hero hero2 = impl.getHero(id + 11111);
        assertNull(hero2);
        hero2 = impl.getHero(id);
        assertNotNull(hero2);
    }
}
