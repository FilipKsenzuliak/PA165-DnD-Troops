///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.fi.muni.pa165.testService;
//
//import cz.fi.muni.pa165.dao.MissionDAO;
//import cz.fi.muni.pa165.dto.MissionDTO;
//import cz.fi.muni.pa165.dto.MissionDTO;
//import cz.fi.muni.pa165.entity.Mission;
//import cz.fi.muni.pa165.serviceImpl.MissionServiceImpl;
//import static org.mockito.Mockito.mock;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import org.dozer.DozerBeanMapper;
//import org.dozer.Mapper;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;
///**
// *
// * @author Tomus
// */
//@Transactional
//@ContextConfiguration(locations = {"classpath:META-INF/testContext.xml"})
//@RunWith(MockitoJUnitRunner.class)
//public class MissionServiceTest {
//    
//    @Inject
//    @InjectMocks
//    private MissionServiceImpl missionService;
//    
//    @Mock
//    private MissionDAO missionDAOMock = mock(MissionDAO.class);
//    
//    public MissionServiceTest(){
//    }
//    
//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//        missionService = new MissionServiceImpl();
//        missionService.setMissionDAO(missionDAOMock);
//    }
//    
//    @After
//    public void release(){
//        missionService = null;
//        missionDAOMock = null;
//    }
//    
//    @Test
//    public void testCreateMission(){
//        Mapper mapper = new DozerBeanMapper();
//        
//        MissionDTO mission = new MissionDTO("Vyvrazdovanie Ogrov","Pobijte vsechny ogry", 200);
//        missionService.createMission(mission);
//        Mockito.verify(missionDAOMock).createMission(mapper.map(mission, Mission.class));
//    }
//    
//    @Test
//    public void testUpdateMission(){
//        Mapper mapper = new DozerBeanMapper();
//        
//        MissionDTO mission = new MissionDTO("Vyvrazdovanie Ogrov","Pobijte vsechny ogry", 200);
//        missionService.createMission(mission);
//        
//        mission.setReward(300);
//        missionService.updateMission(mission);
//        Mockito.verify(missionDAOMock).updateMission(mapper.map(mission, Mission.class));
//    }
//    
//    @Test
//    public void testDeleteMission(){
//        Mapper mapper = new DozerBeanMapper();
//        
//        MissionDTO mission = new MissionDTO("Vyvrazdovanie Ogrov","Pobijte vsechny ogry", 200);
//        
//        missionService.createMission(mission);
//        missionService.deleteMission(mission);
//        
//        Mockito.verify(missionDAOMock).deleteMission(mapper.map(mission, Mission.class));
//    }
//    
//    @Test
//    public void testGetMissionById(){
//        Mapper mapper = new DozerBeanMapper();
//        
//        MissionDTO mission = new MissionDTO("Vyvrazdovanie Ogrov","Pobijte vsechny ogry", 200);
//        missionService.createMission(mission);
//        mission.setId(1L);
//        
//        Mockito.when(missionDAOMock.getMissionById(mission.getId())).thenReturn(mapper.map(mission, Mission.class));
//        MissionDTO returnedMission = missionService.getMissionById(1L);
//        Mockito.verify(missionDAOMock).getMissionById(returnedMission.getId());
//    }
//    
//    
//}
