///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.fi.muni.pa165.testDAO;
//
//import cz.fi.muni.pa165.dao.RoleDAO;
//import cz.fi.muni.pa165.daoImpl.RoleDAOImpl;
//import cz.fi.muni.pa165.entity.Hero;
//import cz.fi.muni.pa165.entity.Mission;
//import cz.fi.muni.pa165.entity.Race;
//import cz.fi.muni.pa165.entity.Role;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Andrej Nemec
// * @UCO 396474
// */
//@Transactional
//@ContextConfiguration("file:src/main/resources/applicationContext-dao-test.xml")
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
//public class RoleDAOTest {
//    private Role r1;
//    private Role r2;
//    private RoleDAO roleDAO;
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
//    private EntityManager em = emf.createEntityManager();
//    
//    @Before
//    public void setup() {
//        r1 = new Role();
//        r1.setName("Wizard");
//        r1.setDescription("Its a kind of magic");
//        r1.setRoleLevel(15);
//        
//        Hero h1;
//        h1 = new Hero();
//        h1.setAge(35L);
//        h1.setRank(20L);
//        h1.setName("Raistlin");
//        h1.setRace(Race.HUMAN);
//        
//        List<Hero> heroes = new ArrayList<Hero>();
//        heroes.add(h1);
//        r1.setHeroes(heroes);
//        
//        r2 = new Role();
//        r2.setName("Paladin");
//        r2.setDescription("For the light!");
//        r2.setRoleLevel(12);
//        
//        Hero h2;
//        h2 = new Hero();
//        h2.setAge(30L);
//        h2.setRank(25L);
//        h2.setName("Sturm");
//        h2.setRace(Race.HUMAN);
//        
//        List<Hero> heroes2 = new ArrayList<Hero>();
//        heroes2.add(h2);
//        r2.setHeroes(heroes2);
//        
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(h1);
//        em.persist(h2);
//        em.getTransaction().commit();
//        em.close();
//        
//        roleDAO = new RoleDAOImpl(emf);
//    }
//    
//    @Test
//    public void testCreateRole() {
//        roleDAO.createRole(r1);
//        roleDAO.createRole(r2);
//        
//        Role role = r1;
//        Role roleDB = roleDAO.getRoleById(r1.getId());
//        
//        assertNotNull(role.getId());
//        assertEquals(role, roleDB);
//        assertEquals(roleDAO.getAllRoles().size(),2);
//    }
//    
//    @Test
//    public void testUpdateRole() {
//        roleDAO.createRole(r1);
//        
//        Role role = r1;
//        role.setName("Warlock");
//        role.setDescription("Demons everywhere");
//        role.setRoleLevel(17);
//        roleDAO.updateRole(role);
//
//        assertEquals("Warlock", r1.getName());
//        assertEquals("Demons everywhere", r1.getDescription());
//        assertEquals(17, r1.getRoleLevel());
//    }
//    
//    @Test
//    public void testDeleteRole() {
//        roleDAO.createRole(r1);
//        roleDAO.createRole(r2);
//        roleDAO.deleteRole(r1);
//        
//        Role roleDB = roleDAO.getRoleById(r1.getId());
//        assertNull(roleDB);
//        assertEquals(roleDAO.getAllRoles().size(), 1); 
//    }
//    
//    @Test
//    public void testGetAllMissions() {
//        roleDAO.createRole(r1);
//        roleDAO.createRole(r2);
//        
//        assertEquals(roleDAO.getAllRoles().size(), 2);
//    }
//    
//    @Test
//    public void testGetRoleById() {
//        roleDAO.createRole(r1);
//        
//        Role role = roleDAO.getRoleById(r1.getId());
//        assertNotNull(role);
//        assertEquals(role.getId(), r1.getId());
//    }
//    
////    @Test
////    public void testFindRoleByName() {
////        roleDAO.createRole(r1);
////        List<Role> role = roleDAO.findRoleByName(r1.getName());
////        assertNotNull(role);
////    }
//}
