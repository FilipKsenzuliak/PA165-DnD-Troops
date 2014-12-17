///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package cz.fi.muni.pa165.testDAO;
//
//import cz.fi.muni.pa165.dao.MissionDAO;
//import cz.fi.muni.pa165.daoImpl.MissionDAOImpl;
//import javax.persistence.EntityManagerFactory;
//import static org.junit.Assert.*;
//
//import cz.fi.muni.pa165.entity.Mission;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//
//
///**
// *
// * @author Filip Ksenzuliak
// * @uco 396072
// */
//@Transactional
//@ContextConfiguration("file:src/main/resources/applicationContext-dao-test.xml")
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
//public class MissionDAOTest{
//        
//    public MissionDAOTest() {
//    }
//
//    private MissionDAOImpl missionDAO;
//    
//    private Mission m1;
//    private Mission m2;
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
//    private EntityManager em;
//
//    @Before
//    public void setup() {
//        m1 = new Mission();
//        m1.setName("Slay them all!");
//        m1.setObjective("Slay everyone");
//        m1.setReward(10);
//
//        m2 = new Mission();
//        m2.setName("Relaxing!");
//        m2.setObjective("Just relax");
//        m2.setReward(1);
//        
//        em = emf.createEntityManager();
//        missionDAO = new MissionDAOImpl();
//        missionDAO.setEntityManager(em);
//    }
//
//    @Test
//    public void testCreateMission() {
//        em.getTransaction().begin();
//        missionDAO.createMission(m1);
//        Mission mission = m1;
//        Mission missionDB = missionDAO.getMissionById(m1.getId());
//        em.getTransaction().commit();
//        
//        assertNotNull(mission.getId());
//        assertEquals(mission, missionDB);
//    }
//    
//    @Test
//    public void testUpdateMission() {
//        em.getTransaction().begin();
//        missionDAO.createMission(m1);
//        Mission mission = m1;
//        mission.setName("Don't slay anyone");
//        mission.setObjective("Keep away from everyone");
//        mission.setReward(1);
//        missionDAO.updateMission(mission);
//        em.getTransaction().commit();
//        
//        assertEquals("Don't slay anyone", m1.getName());
//        assertEquals("Keep away from everyone", m1.getObjective());
//        assertEquals(1, m1.getReward());
//    }
//    
//    @Test
//    public void testDeleteMission() {
//        em.getTransaction().begin();
//        missionDAO.createMission(m1);
//        Mission mission = m1;
//        missionDAO.deleteMission(mission);
//        Mission missionDB = missionDAO.getMissionById(mission.getId());
//        em.getTransaction().commit();
//        
//        assertNull(missionDB);
//    }
//    
//    @Test
//    public void testGetMission() {
//        em.getTransaction().begin();
//        missionDAO.createMission(m2);
//        Mission mission = missionDAO.getMissionById(m2.getId());
//        em.getTransaction().commit();        
//
//        assertNotNull(mission);
//        assertEquals(mission.getId(), m2.getId());
//    }
//    
//    @Test
//    public void testGetAllMissions() {
//        em.getTransaction().begin();
//        missionDAO.createMission(m1);
//        em.getTransaction().commit();
//        
//        em.getTransaction().begin();
//        missionDAO.createMission(m2);
//        em.getTransaction().commit();
//        
//        int count = missionDAO.getAllMissions().size();
//        assertEquals(count, 2);
//    }
//    
//    @Test
//    public void testFindMissionByName() {
//        missionDAO.createMission(m2);
//        List<Mission> mission = missionDAO.findMissionByName(m2.getName());
//        
//        assertNotNull(mission);
//    }
//}
