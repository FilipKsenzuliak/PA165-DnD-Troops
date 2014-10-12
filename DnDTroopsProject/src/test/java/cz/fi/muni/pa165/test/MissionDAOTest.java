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
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */

public class MissionDAOTest{
        
    public MissionDAOTest() {
    }

    private Mission m1;
    private Mission m2;
    private MissionDAO missionDAO;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");

    @Before
    public void setup() {
        m1 = new Mission();
        m1.setName("Slay them all!");
        m1.setObjective("Slay everyone");
        m1.setReward(10);

        m2 = new Mission();
        m2.setName("Relaxing!");
        m2.setObjective("Just relax");
        m2.setReward(1);

        missionDAO = new MissionDAOImpl(emf);
    }

    @Test
    public void testCreateMission() {
        missionDAO.createMission(m1);

        Mission mission = m1;
        Mission missionDB = missionDAO.getMissionById(m1.getId());

        assertEquals(mission, missionDB);
    }
    
    
}
