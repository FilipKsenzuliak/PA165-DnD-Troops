/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.daoImpl.RoleDAOImpl;
import cz.fi.muni.pa165.entity.Role;
import java.util.List;
import java.util.Random;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Andrej Nemec
 * @396474  
 */
public class RoleDAOTest {
    /*private final RoleDAO testRole;
    
    public RoleDAOTest() {
        this.testRole = new RoleDAOImpl();
    }
    
    private Role createTestRole() {
        Role role = new Role();
        role.setName("Barbarian "+ role.getId());
        role.setDescription("Description");
        role.setRoleLevel(new Random().nextInt());
        return role;
    }
    
    private Role createSomeRole() {
        Role role = createTestRole();
        testRole.createRole(role);
        return role;
    }
    
    @Test
    public void createRoleTest() {
        Role role = createSomeRole();
        Assert.assertEquals(testRole.getRoleById(role.getId()), role, "Expected equal role");
    }
    
    @Test
    public void updateRoleTest() {
        Role role = createSomeRole();
        role.setDescription("Test description");
        role.setName("Rogue " + role.getId());
        
        testRole.updateRole(role);
        
        Role databaseRole = testRole.getRoleById(role.getId());
        Assert.assertEquals(databaseRole.getDescription(), "Test description", "Expected updated description");
        Assert.assertEquals(databaseRole.getName(), "Rogue " + role.getId(), "Expected updated name");
    }
    
    @Test
    public void deleteRoleTest() {
        Role role = createSomeRole();
        testRole.deleteRole(role);
        Role databaseRole = testRole.getRoleById(role.getId());
        Assert.assertNull(databaseRole, "Not deleted");
    }
    
    @Test
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
    }
    
    @Test
    public void getRoleByIdTest() {
        Role role = createSomeRole();
        Role databaseRole = testRole.getRoleById(role.getId());
        Assert.assertNotNull(databaseRole);
    }   */
}
