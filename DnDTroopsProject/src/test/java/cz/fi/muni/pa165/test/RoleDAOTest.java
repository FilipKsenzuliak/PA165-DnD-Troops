/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.daoImpl.HeroDAOImpl;
import cz.fi.muni.pa165.daoImpl.RoleDAOImpl;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Mission;
import cz.fi.muni.pa165.entity.Race;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.entity.Troop;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Andrej Nemec
 * @396474  
 */
@ContextConfiguration(classes=DaoContext.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleDAOTest {
    @PersistenceUnit
    public EntityManagerFactory emf;
    
    private RoleDAOImpl impl;
    private EntityManager em;
    
    public RoleDAOTest() {
        
    }
    
    @DirtiesContext
    @BeforeMethod
    public void setup(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        impl = new RoleDAOImpl();

        Hero hero = new Hero();
        hero.setAge(35L);
        hero.setRank(20L);
        hero.setName("Andrej");
        hero.setRace(Race.DWARF);
            
        Role role1 = new Role();
        role1.setName("Wizard");
        role1.setDescription("Guy with a staff");
        role1.setRoleLevel(5);

        Role role2 = new Role();
        role2.setName("Rogue");
        role2.setDescription("Guy with dual daggers");
        role2.setRoleLevel(4);     
        
        List<Role> roles = new ArrayList<Role>();
        roles.add(role2);
        hero.setRole(roles);

        em.persist(role1);
        em.persist(role2);
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void createRoleTest() {       
        Role role3 = new Role();
        role3.setName("Paladin");
        role3.setDescription("Guy who embraces light");
        role3.setRoleLevel(12);
        
        try {
            em.createQuery("DELETE FROM Role").executeUpdate();
            List<Role> roles = em.createQuery("SELECT r FROM Role r").getResultList();
            assertEquals(0, roles.size());

            impl.createRole(role3);
            roles = em.createQuery("SELECT r FROM Role r").getResultList();
            assertNotNull(roles);
            assertEquals(1, roles.size());
            Role roleGet = roles.get(0);
            assertNotNull(roleGet);
            assertEquals("Paladin", roleGet.getName());
            assertEquals("Guy who embraces light", roleGet.getDescription());
        } finally {
            em.close();
        }
    }
    
    @Test
    public void updateTest() {
        Role role = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().get(0);
        int roleLevel = role.getRoleLevel();
        role.setRoleLevel(10);
        impl.updateRole(role);
        role = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().get(0);
        assertNotEquals(roleLevel, role.getRoleLevel());
        
        role.setId(null);
        impl.updateRole(role);
        Long id = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().get(0).getId();
        assertNotEquals(null, id);
    }
    
    @Test
    public void deleteTest() {
        int count = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().size();
        Role role = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().get(0);
        
        impl.deleteRole(role);
        int count2 = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().size();
        assertNotEquals(count, count2);
    }
    
    /*@Test
    public void getAllRolesTest() {
        List<Role> allRoles = testRole.getAllRoles();
        if(allRoles.size() > 0) {
            for(Role r : allRoles) {
                testRole.deleteRole(r);
            }
        }
        
        allRoles = testRole.getAllRoles();
        Assert.assertEquals(allRoles.size(), 0);
        
        for(int i = 0; i < 10; i++) {
            createSomeRole();
        }
        allRoles = testRole.getAllRoles();
        Assert.assertEquals(allRoles.size(), 10);
    }*/
    
    @Test
    public void getByIdTest() {
        Role role = em.createQuery("SELECT r FROM Role r", Role.class).getResultList().get(0);
        Long id = role.getId();
        Role role2 = impl.getRoleById(id + 11111);
        assertNull(role2);
        role2 = impl.getRoleById(id);
        assertNotNull(role2);
    } 
}
