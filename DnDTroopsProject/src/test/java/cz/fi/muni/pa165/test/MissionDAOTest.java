/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.dao.MissionDAO;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import static org.junit.Assert.*;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.entity.Mission;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml"})
@TransactionConfiguration(defaultRollback=true)
public class MissionDAOTest extends AbstractTestNGSpringContextTests {
        
    @Autowired
    MissionDAO missionDAO;
    
    public MissionDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindAllAccounts() {
        List<Mission> miss = missionDAO.getAllMissions();
        assertEquals("Check DB is empty first", 0, miss.size());
        Mission m = new Mission();
        m.setName("nieco");
        m.setObjective("kill it with fire");
        m.setReward(150);
        missionDAO.createMission(m);
        miss = missionDAO.getAllMissions();
        assertEquals("check Account has been created", 1, miss.size());
    }
}
