/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.testService;

import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.dto.HeroDTO;
import cz.fi.muni.pa165.dto.MissionDTO;
import cz.fi.muni.pa165.dto.TroopDTO;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.serviceImpl.TroopServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.dozer.Mapper;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrej
 */

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = {"classpath:META-INF/testContext.xml"})
@RunWith(MockitoJUnitRunner.class)
public class TroopServiceTest {
    
    @Autowired
    @InjectMocks
    private TroopServiceImpl troopService;
    
    @Mock
    private TroopDAO troopDAOMock = mock(TroopDAO.class);
    
    @Before
    public void setup() {
       MockitoAnnotations.initMocks(this);
       troopService = new TroopServiceImpl();
       troopService.setTroopDAO(troopDAOMock);
    }
    
    @After
    public void release() {
        troopService = null;
        troopDAOMock = null;
    }
    
    @Test
    public void smt(){
        
    }
    
    @Test
    public void testCreateTroop() {
        Mapper mapper = new DozerBeanMapper();
        MissionDTO mission = new MissionDTO(); //awaiting implementation
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        TroopDTO troop = new TroopDTO("Punishers", mission, 5000L, heroes);
        
        hero = new HeroDTO();
        heroes = new ArrayList();
        heroes.add(hero);
        TroopDTO troop2 = new TroopDTO("GuysFromHell", mission, 20000L, heroes);
        
        troopService.createTroop(troop);
        Mockito.verify(troopDAOMock).createTroop(mapper.map(troop, Troop.class));
    }
    
    @Test
    public void testUpdateTroop() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        MissionDTO mission = new MissionDTO();
        TroopDTO troop = new TroopDTO("Punishers", mission, 5000L, heroes);
        
        troopService.createTroop(troop);
        troop.setName("NotPunishers");
        troopService.updateTroop(troop);
        Mockito.verify(troopDAOMock).updateTroop(mapper.map(troop, Troop.class));
    }
    
    @Test
    public void testDeleteTroop() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        MissionDTO mission = new MissionDTO();
        TroopDTO troop = new TroopDTO("Punishers", mission, 5000L, heroes);
        
        troopService.createTroop(troop);
        troopService.deleteTroop(troop);
        Mockito.verify(troopDAOMock).removeTroop(mapper.map(troop, Troop.class));
    }
    
    @Test
    public void testGetTroopById() {
        Mapper mapper = new DozerBeanMapper();
        HeroDTO hero = new HeroDTO();
        List<HeroDTO> heroes = new ArrayList();
        heroes.add(hero);
        MissionDTO mission = new MissionDTO();
        TroopDTO troop = new TroopDTO("Punishers", mission, 5000L, heroes);
        
        troopService.createTroop(troop);
        TroopDTO troopT = troopService.getTroopById(1L);
        Mockito.verify(troopDAOMock).getTroop(1L);
    }
}
