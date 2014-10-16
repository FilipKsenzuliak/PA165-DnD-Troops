/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.entity.Mission;
import cz.fi.muni.pa165.entity.Troop;
import cz.fi.muni.pa165.daoImpl.TroopDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco 324662
 */
public class TroopDAOTest {
    
    private Troop troop1,troop2,troop3;
    private Mission mission1,mission2,mission3;
    private TroopDAO troopDAO;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    private EntityManager em = emf.createEntityManager();
    
    @Before
    public void setup(){
        troop1 = new Troop();
        troop2 = new Troop();
        troop3 = new Troop();
        
        mission1 = new Mission();
        mission2 = new Mission();
        mission3 = new Mission();
        
        troop1.setName("Berserkovia");
        troop2.setName("Polozombici");
        troop3.setName("WoA");
        
        troop1.setAmountOfMoney(550L);
        troop2.setAmountOfMoney(750L);
        troop3.setAmountOfMoney(2750L);
        
        
        mission1.setName("Plienenie");
        mission2.setName("Zamorovanie");
        mission3.setName("RaidSWP");
        
        mission1.setObjective("Vypliente vsetko v okruhu 10Km");
        mission2.setObjective("Zamorte rieku Nimreth");
        mission3.setObjective("Clear Sunwell Plateau");
        
        mission1.setReward(1500);
        mission2.setReward(375);
        mission3.setReward(2500);
        
        troop1.setMission(mission1);
        troop2.setMission(mission2);
        troop3.setMission(mission3);
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(troop1);
        em.persist(troop2);
        em.persist(troop3);
        em.persist(mission1);
        em.persist(mission2);
        em.persist(mission3);
        em.getTransaction().commit();
        em.close();
        
        troopDAO = new TroopDAOImpl(emf);
    }
    
    @Test
    public void testCreateTroop(){
        System.out.println("***");
        troopDAO.createTroop(troop1);
        
        Troop troopDB = troopDAO.getTroop(troop1.getId());
        assertNotNull(troopDB.getId());
        assertEquals("Berserkovia", troopDB.getName());
        troopDAO.removeTroop(troop1);
    }
    /**
    @Test
    public void testUpdateTroop(){
        troopDAO.createTroop(troop1);
        String name = troop1.getName();
        Long goldz = troop1.getAmountOfMoney();
        Troop troopDB = troopDAO.getTroop(troop1.getId());
        troopDB.setName("Honikladovia");
        troopDAO.updateTroop(troopDB);
        Troop troopDB2 = troopDAO.getTroop(troopDB.getId());
        
        assertNotEquals(name, troopDB2.getName());
        assertEquals(goldz, troopDB2.getAmountOfMoney());
    }
    
    @Test
    public void testRemoveTroop() {
        troopDAO.createTroop(troop1);
        troopDAO.createTroop(troop2);
        troopDAO.removeTroop(troop1);
        
        List<Troop> troops = troopDAO.getAllTroops();
        assertNotNull(troops);
        assertEquals(troops.size(), 1);        
    }
    
    @Test
    public void testGetTroop() {
        troopDAO.createTroop(troop3);
        Troop troop = troopDAO.getTroop(troop3.getId());
        assertNotNull(troop);
    }
    
    @Test
    public void testFindTroopByName() {
        troopDAO.createTroop(troop3);
        List<Troop> troop = troopDAO.findTroopByName(troop3.getName());
        assertNotNull(troop);
    }
    */
}
