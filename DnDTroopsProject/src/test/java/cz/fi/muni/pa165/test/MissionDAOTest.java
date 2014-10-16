/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.dao.MissionDAO;
import cz.fi.muni.pa165.daoImpl.MissionDAOImpl;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.*;

import cz.fi.muni.pa165.entity.Mission;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
public class MissionDAOTest{
     
    private Mission m1;
    private Mission m2;
    private Mission m3;
    private MissionDAO missionDAO;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");

    @Before
    public void setup() {
        m1 = new Mission();
        m1.setName("Slay them all!");
        m1.setObjective("Just some casual slaying.");
        m1.setReward(10);

        m2 = new Mission();
        m2.setName("Relaxing!");
        m2.setObjective("Calm and relax.");
        m2.setReward(1);
        
        m3 = new Mission();
        m3.setName("Money!");
        m3.setObjective("Find the treasure near coast.");
        m3.setReward(150);

        missionDAO = new MissionDAOImpl(emf);
    }

    @Test
    public void testCreateMission() {
        missionDAO.createMission(m1);
        missionDAO.createMission(m2);
        
        Mission mission = m1;
        Mission missionDB = missionDAO.getMissionById(m1.getId());
        
        assertNotNull(mission.getId());
        assertEquals(mission, missionDB);
        assertEquals(missionDAO.getAllMissions().size(),2);
    }
    
    @Test
    public void testUpdateMission() {
        missionDAO.createMission(m1);
        
        Mission mission = m1;
        mission.setName("Don't slay anyone");
        mission.setObjective("Keep away from everyone");
        mission.setReward(1);
        missionDAO.updateMission(mission);

        assertEquals("Don't slay anyone", m1.getName());
        assertEquals("Keep away from everyone", m1.getObjective());
        assertEquals(1, m1.getReward());
    }
    
    @Test
    public void testDeleteMission() {
        missionDAO.createMission(m1);
        missionDAO.createMission(m2);
        missionDAO.deleteMission(m1);
        
        Mission missionDB = missionDAO.getMissionById(m1.getId());
        assertNull(missionDB);
        assertEquals(missionDAO.getAllMissions().size(), 1); 
    }
     
    @Test
    public void testGetAllMissions() {
        missionDAO.createMission(m1);
        missionDAO.createMission(m2);
        missionDAO.createMission(m3);
 
        assertEquals(missionDAO.getAllMissions().size(), 3);
    }
    
    @Test
    public void testGetMissionById() {
        missionDAO.createMission(m1);
        
        Mission mission = missionDAO.getMissionById(m1.getId());
        assertNotNull(mission);
        assertEquals(mission.getId(), m1.getId());
    }
    
    @Test
    public void testFindMissionByName() {
        missionDAO.createMission(m1);
        
        List<Mission> mission = missionDAO.findMissionByName("Slay them all!");
        assertEquals(mission.get(0).getName(), "Slay them all!");
        assertEquals(mission.get(0).getObjective(), "Just some casual slaying.");
        assertEquals(mission.get(0).getReward(), 10);
    }
}
