/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.testService;
import cz.fi.muni.pa165.dao.RoleDAO;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.serviceImpl.RoleServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.dozer.Mapper;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author David
 */
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = {"classpath:META-INF/testContext.xml"})
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    
    @Inject
    @InjectMocks
    private RoleServiceImpl roleService;
    
    @Mock
    private RoleDAO roleDAOMock = mock(RoleDAO.class);
    
    public RoleServiceTest() {
        
    }
    
    @Before
    public void setup() {
       MockitoAnnotations.initMocks(this);
       roleService = new RoleServiceImpl();
       roleService.setRoleDAO(roleDAOMock);
    }
    
    @After
    public void release() {
        roleService = null;
        roleDAOMock = null;
    }
    
    @Test
    public void testCreateRole() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        RoleDTO role = new RoleDTO(heroes, "Magician", "Magician uses spells.");
        
        hero = new HeroDTO();
        heroes = new ArrayList();
        heroes.add(hero);
        RoleDTO role2 = new RoleDTO(heroes, "Barbar", "Barbar is a fyzical damage type warrior.");
        
        roleService.createRole(role);
        Mockito.verify(roleDAOMock).createRole(mapper.map(role, Role.class));
    }
    
    @Test
    public void testUpdateRole() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        RoleDTO role = new RoleDTO(heroes, "Magician", "Magician uses spells.");
        
        roleService.createRole(role);
        role.setDescription("Magician does not use spells.");
        roleService.updateRole(role);
        Mockito.verify(roleDAOMock).updateRole(mapper.map(role, Role.class));
    }
    
    @Test
    public void testDeleteRole() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        RoleDTO role = new RoleDTO(heroes, "Magician", "Magician uses spells.");
        
        roleService.createRole(role);
        roleService.deleteRole(role);
        Mockito.verify(roleDAOMock).deleteRole(mapper.map(role, Role.class));
    }
    
    @Test
    public void testGetRoleById() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        RoleDTO role = new RoleDTO(heroes, "Magician", "Magician uses spells.");
        roleService.createRole(role);
        role.setId(1L);
                
        Mockito.when(roleDAOMock.getRoleById(role.getId())).thenReturn(mapper.map(role, Role.class));
        
        RoleDTO roleR = roleService.getRoleById(1L);
        Mockito.verify(roleDAOMock).getRoleById(1L);
    }
}
