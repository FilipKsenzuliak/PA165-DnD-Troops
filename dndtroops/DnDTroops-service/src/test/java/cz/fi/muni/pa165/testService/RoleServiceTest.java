///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package cz.fi.muni.pa165.testService;
//import cz.fi.muni.pa165.dao.RoleDAO;
//import cz.fi.muni.pa165.api.dto.RoleDTO;
//import cz.fi.muni.pa165.entity.Role;
//import cz.fi.muni.pa165.serviceImpl.RoleServiceImpl;
//import java.util.ArrayList;
//import java.util.List;
//import org.dozer.Mapper;
//import org.mockito.InjectMocks;
//import static org.mockito.Matchers.notNull;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import static org.mockito.MockitoAnnotations.initMocks;
//import org.mockito.Spy;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
///**
// *
// * @author 
// * 
// * Test class for testing Role service
// */
//@DirtiesContext
//@ContextConfiguration(locations = {"classpath:/applicationContext-service-test.xml"})
//public class RoleServiceTest extends AbstractTestNGSpringContextTests{
//    
//    @InjectMocks       
//    RoleServiceImpl roleService;
//   
//    @Mock
//    RoleDAO roleDao;
//    
//    @Autowired
//    @Spy
//    private Mapper mapper;
//    
//    @BeforeMethod
//    private void start(){
//        initMocks(this);
//        Role role = createNewRole();
//        Mockito.when(roleDao.createRole(role)).thenAnswer(new Answer<Role>() {
//
//            @Override
//            public Role answer(InvocationOnMock invocation) throws Throwable {
//                Role mockedRole = invocation.getArgumentAt(0, Role.class);
//                mockedRole.setId(1l);
//                return mockedRole;
//            }
//        });
//        
//        Mockito.when(roleDao.getRoleById(1l)).thenAnswer(new Answer<Role>() {
//
//            @Override
//            public Role answer(InvocationOnMock invocation) throws Throwable {
//                Role mockedRole = createNewRole();
//                mockedRole.setId(1l);
//                return mockedRole;
//            }
//        });
//        
//        Mockito.when(roleDao.retrieveRoleByName("Witcher")).thenAnswer(new Answer<Role>() {
//
//            @Override
//            public Role answer(InvocationOnMock invocation) throws Throwable {
//                Role mockedRole = createNewRole();
//                mockedRole.setId(1l);
//                return mockedRole;
//            }
//        });
//        
//        Mockito.when(roleDao.retrieveRoleByName("Warrior")).thenAnswer(new Answer<Role>() {
//
//            @Override
//            public Role answer(InvocationOnMock invocation) throws Throwable {
//                Role mockedRole = createNewRole();
//                mockedRole.setId(1l);
//                mockedRole.setName("Warrior");
//                mockedRole.setDescription("útoèí vepøedu, èasto používán jako tank");
//                return mockedRole;
//            }
//        });
//        
//        Mockito.when(roleDao.updateRole((Role)notNull())).thenAnswer(new Answer<Role>() {
//
//            @Override
//            public Role answer(InvocationOnMock invocation) throws Throwable {
//                Role mockedRole = invocation.getArgumentAt(0, Role.class);
//                return mockedRole;
//            }
//        });
//        
//        Mockito.when(roleDao.deleteRole((Role)notNull())).thenReturn(Boolean.TRUE);
//        
//        Mockito.when(roleDao.getAllRoles()).thenAnswer(new Answer<List<Role>>() {
//
//            @Override
//            public List<Role> answer(InvocationOnMock invocation) throws Throwable {
//                List<Role> roles = createGroupRoles();
//                return roles;
//            }
//        });
//    }
//    
//    private Role createNewRole() {
//        Role role = new Role();
//        role.setName("Witcher");
//        return role;
//    }
//    
//    private RoleDTO createNewRoleDTO() {
//        RoleDTO role = new RoleDTO();
//        role.setName("Witcher");
//        return role;
//    }
//    
//    @Test
//    public void createRoleTest() {
//        RoleDTO role = createNewRoleDTO();
//        role = roleService.createRole(role);
//        Assert.assertTrue(role.getId() == 1);
//        Assert.assertEquals(roleService.getRoleById(role.getId()), role);
//        System.out.println("Test createRole DTO run succesfull");
//    }
//    
//    @Test
//    public void retrieveRoleByIdTest() {
//        RoleDTO role = roleService.createRole(createNewRoleDTO());
//        RoleDTO dbRole = roleService.getRoleById(role.getId());
//        Assert.assertNotNull(dbRole);
//        System.out.println("Test retrieveRoleById DTO run succesfull");
//    }
//    
//    @Test
//    public void retrieveRoleByNameTest() {
//        RoleDTO role = roleService.createRole(createNewRoleDTO());
//        RoleDTO dbRole = roleService.retrieveRoleByName(role.getName());
//        Assert.assertNotNull(dbRole);
//        System.out.println("Test retrieveRoleByName DTO run succesfull");
//    }
//    
//    @Test
//    public void updateRoleTest() {
//        RoleDTO role = roleService.createRole(createNewRoleDTO());
//        role.setName("Warrior");
//        role.setDescription("útoèí vepøedu, èasto používán jako tank");
//        roleService.updateRole(role);
//        RoleDTO dbRole = roleService.retrieveRoleByName("Warrior");
//        Assert.assertEquals(dbRole.getName(), "Warrior");
//        Assert.assertEquals(dbRole.getDescription(), "útoèí vepøedu, èasto používán jako tank");
//        System.out.println("Test updateRole DTO run succesfull");
//    }
//    
//    @Test
//    public void deleteRoleTest() {
//        RoleDTO role = roleService.createRole(createNewRoleDTO());
//        Assert.assertTrue(roleService.deleteRole(role));
//        System.out.println("Test deleteRole DTO run succesfull");
//    }
//    
//    private List<Role> createGroupRoles() {
//        List<Role> roles = new ArrayList();
//        for(int i = 1; i < 6; i++) {
//            Role role = createNewRole();
//            role.setName(role.getName()+"_"+i);
//            role.setId((long)i);
//            roles.add(role);
//        }
//        return roles;
//    }
//    
//    private void createGroupRolesDTO() {
//        for(int i = 1; i < 6; i++) {
//            RoleDTO role = roleService.createRole(createNewRoleDTO());
//            role.setName(role.getName()+"_"+i);
//            role.setId((long)i);
//            roleService.updateRole(role);
//        }
//    }
//    
//    @Test
//    public void retrieveAllRolesTest() {
//        createGroupRolesDTO();
//        List<RoleDTO> roles;    
//        roles = roleService.getAllRoles();
//        Assert.assertEquals(roles.size(), 5);
//        System.out.println("Test retrieveAllRoles DTO run succesfull");
//    }
//    
//    @Test
//    public void deleteAllRolesTest() {
//        createGroupRolesDTO();
//        Assert.assertTrue(roleService.deleteAllRoles());
//        System.out.println("Test deleteAllRoles DTO run succesfull");
//    }
//    
//}
