/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.testService;

import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.RoleDTO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Hero;
import cz.fi.muni.pa165.entity.Race;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.serviceImpl.HeroServiceImpl;
import static org.mockito.Mockito.mock;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Filip Ksenzuliak
 */
@Transactional
@ContextConfiguration(locations = {"classpath:META-INF/testContext.xml"})
@RunWith(MockitoJUnitRunner.class)
public class HeroServiceTest {
    
    @Autowired
    @InjectMocks
    private HeroServiceImpl heroService;
    
    @Mock
    private HeroDAO heroDAOMock = mock(HeroDAO.class);
    
    public HeroServiceTest() {
    }
    
    @Before
    public void setup() {
       MockitoAnnotations.initMocks(this);
       heroService = new HeroServiceImpl();
       heroService.setHeroDAO(heroDAOMock);
    }
    
    @After
    public void release() {
        heroService = null;
        heroDAOMock = null;
    }
    
    @Test
    public void testCreateHero() {
        Mapper mapper = new DozerBeanMapper();
        
        TroopDTO troop = new TroopDTO();
        RoleDTO role = new RoleDTO();                
        List<RoleDTO> roles = new ArrayList();
        roles.add(role);
        
        HeroDTO hero = new HeroDTO(Race.ELF, 150L, 10L, "Jozef",  troop, roles);
        heroService.createHero(hero);
        Mockito.verify(heroDAOMock).createHero(mapper.map(hero, Hero.class));
    }
    
    @Test
    public void testUpdateHero() {
        Mapper mapper = new DozerBeanMapper();
        
        TroopDTO troop = new TroopDTO();
        RoleDTO role = new RoleDTO();                
        List<RoleDTO> roles = new ArrayList();
        roles.add(role);
        
        HeroDTO hero = new HeroDTO(Race.ELF, 150L, 10L, "Jozef",  troop, roles);
        heroService.createHero(hero);
        
        hero.setRank(15L);
        heroService.updateHero(hero);
        Mockito.verify(heroDAOMock).updateHero(mapper.map(hero, Hero.class));
    }

}