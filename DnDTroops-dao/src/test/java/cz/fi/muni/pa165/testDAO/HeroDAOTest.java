///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package cz.fi.muni.pa165.testDAO;
//
//
//import cz.fi.muni.pa165.dao.HeroDAO;
//import cz.fi.muni.pa165.dao.MissionDAO;
//import cz.fi.muni.pa165.dao.RoleDAO;
//import cz.fi.muni.pa165.dao.TroopDAO;
//import cz.fi.muni.pa165.daoImpl.HeroDAOImpl;
//import cz.fi.muni.pa165.daoImpl.MissionDAOImpl;
//import cz.fi.muni.pa165.daoImpl.RoleDAOImpl;
//import cz.fi.muni.pa165.daoImpl.TroopDAOImpl;
//import javax.persistence.EntityManagerFactory;
//import static org.junit.Assert.*;
//
//import cz.fi.muni.pa165.entity.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
///**
// *
// * @author David Hubac
// */
//@Transactional
//@ContextConfiguration("file:src/main/resources/applicationContext-dao-test.xml")
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
//public class HeroDAOTest {
//    
//    private Hero h1;
//    private Hero h2;
//    private HeroDAOImpl heroDAO;
//    private EntityManager em;
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
//
//    @Before
//    public void setup() {
//        h1 = new Hero();
//        h1.setAge(35L);
//        h1.setRank(20L);
//        h1.setName("Andrej");
//        h1.setRace(Race.DWARF);
//        
//        Role role = new Role();
//        role.setName("Wizard");
//        role.setDescription("This is setup description.");
//        role.setRoleLevel(4);
//        List<Role> roles = new ArrayList<Role>();
//        roles.add(role);
//        h1.setRole(roles);
//        
//        Troop troop = new Troop();
//        troop.setAmountOfMoney(100000L);
//        
//        Mission mission = new Mission();
//        mission.setName("Ryan.");
//        mission.setObjective("Save private Ryan.");
//        mission.setReward(10000);
//        
//        troop.setMission(mission);
//        h1.setTroop(troop);
//        
//        //Second Hero
//        h2 = new Hero();
//        h2.setAge(38L);
//        h2.setRank(22L);
//        h2.setName("Filip");
//        h2.setRace(Race.ELF);
//        
//        Role role2 = new Role();
//        role2.setName("ROGUE");
//        role2.setDescription("This is setup description number two.");
//        role2.setRoleLevel(42);
//        List<Role> roles2 = new ArrayList<Role>();
//        roles2.add(role);
//        h2.setRole(roles2);
//        
//        Troop troop2 = new Troop();
//        troop2.setAmountOfMoney(1000L);
//        
//        Mission mission2 = new Mission();
//        mission2.setName("Paul.");
//        mission2.setObjective("Save private Paul.");
//        mission2.setReward(100);
//        
//        troop2.setMission(mission2);
//        h2.setTroop(troop2);
//        
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(role);
//        em.persist(mission);
//        em.persist(troop);
//        em.persist(role2);
//        em.persist(mission2);
//        em.persist(troop2);
//        em.getTransaction().commit();
//        
//        heroDAO = new HeroDAOImpl();
//        
//        heroDAO.setEm(em);
//    }
//    
//    @After
//    public void tearDown() {
//        em.close();
//    }
//
//    @Test
//    public void testCreateHero() {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        Hero heroDB = heroDAO.getHeroById(h1.getId());
//        
//        assertNotNull(heroDB);
//        assertEquals("Andrej", heroDB.getName());
//        
//        
//    }
//    
//    @Test
//    public void testUpdateHero() 
//    {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        String name = h1.getName();
//        Long rank = h1.getRank();
//        Hero heroDB = heroDAO.getHeroById(h1.getId());
//        heroDB.setName("Killer");
//        heroDAO.updateHero(heroDB);
//        Hero heroDB2 = heroDAO.getHeroById(heroDB.getId());
//
//        assertNotEquals(name, heroDB2.getName());
//        assertEquals(rank, heroDB2.getRank());
//    }
//    
//    @Test
//    public void testRemoveHero() {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        em.getTransaction().begin();
//        heroDAO.createHero(h2);
//        em.getTransaction().commit();
//        
//        em.getTransaction().begin();
//        heroDAO.deleteHero(h1);
//        em.getTransaction().commit();
//        
//        List<Hero> heroes = heroDAO.getAllHeroes();
//        assertNotNull(heroes);
//        assertEquals(heroes.size(), 1);        
//    }
//    
//    @Test
//    public void testGetAllHeroes() {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        em.getTransaction().begin();
//        heroDAO.createHero(h2);
//        em.getTransaction().commit();
//        
//        int count = heroDAO.getAllHeroes().size();
//        assertEquals(count, 2);
//    }
//    
//    @Test
//    public void testGetHero() {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        Hero hero = heroDAO.getHeroById(h1.getId());
//        assertNotNull(hero);
//    }
//    
//    @Test
//    public void testFindHeroByName() {
//        em.getTransaction().begin();
//        heroDAO.createHero(h1);
//        em.getTransaction().commit();
//        
//        List<Hero> hero = heroDAO.findHeroByName(h1.getName());
//        assertNotNull(hero);
//    }
//}
