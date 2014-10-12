package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.TroopDAO;
import cz.fi.muni.pa165.daoImpl.TroopDAOImpl;
import cz.fi.muni.pa165.entity.Troop;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cz.fi.muni.pa165.entity.Mission;

/**
 *
 * @author Tomas Javorsky a.k.a. Tomus
 * @uco: 324662
 */
@ContextConfiguration(classes=DaoContext.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TroopDAOTest {
    
    @PersistenceUnit
    public EntityManagerFactory emf;
    
    @DirtiesContext
    @BeforeMethod
    public void setup(){
    EntityManager em = emf.createEntityManager();
		
    em.getTransaction().begin();
    
    Troop troop1 = new Troop();
    troop1.setName("Lovci Lebek");
    Mission mission1 = new Mission();
    mission1.setName("Lovenie lebiek");
    mission1.setObjective("vrazdite vsetko okolo seba a doneste 20 lebiek");
    mission1.setReward(500);
    troop1.setMission(mission1);
    troop1.setAmountOfMoney(750);
    
    Troop troop2 = new Troop();
    troop2.setName("Zealoti");
    Mission mission2 = new Mission();
    mission2.setName("Obracanie na vieru");
    mission2.setObjective("Konvertujte alebo zabite 20 obyvatelov Elkie");
    mission2.setReward(1500);
    troop2.setMission(mission1);
    troop2.setAmountOfMoney(2500);
    
    Troop troop3 = new Troop();
    troop3.setName("Zealoti");
    Mission mission3 = new Mission();
    mission3.setName("Obracanie na vieru");
    mission3.setObjective("Konvertujte alebo zabite 20 obyvatelov Elkie");
    mission3.setReward(1500);
    troop3.setMission(mission1);
    troop3.setAmountOfMoney(2500);
    
    em.persist(troop1);
    em.persist(troop2);
    em.persist(troop3);
    em.getTransaction().commit();
    em.close();
    }
    
    @Test
	public void findAllTroops() {
		EntityManager em = emf.createEntityManager();
		List<Troop> troops = em.createQuery("SELECT p FROM Troop p",Troop.class).getResultList();
		em.close();
		
		Assert.assertEquals(troops.size(), 3);
    }
}
