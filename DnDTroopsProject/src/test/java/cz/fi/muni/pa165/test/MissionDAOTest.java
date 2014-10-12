/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.test;

import cz.fi.muni.pa165.DaoContext;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.spi.LoadState;

import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.entity.Mission;

/**
 *
 * @author Filip Ksenzuliak
 * @uco 396072
 */
@ContextConfiguration(classes=DaoContext.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class MissionDAOTest {
        
        @PersistenceUnit
	public EntityManagerFactory emf;
	
	@DirtiesContext
	@BeforeMethod
	public void setup(){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Mission mis1 = new Mission();
		mis1.setName("Dragon above us");		
		mis1.setObjective("Find that filthy dragon and slay him");
		mis1.setReward(150);
		
		Mission mis2 = new Mission();
		mis2.setName("Not a dragon above us");		
		mis2.setObjective("Find something and slay it");
		mis2.setReward(50);
		
		Mission mis3 = new Mission();
		mis3.setName("Relaxing");		
		mis3.setObjective("Take a break and relax");
		mis3.setReward(0);
		
		em.persist(mis1);
		em.persist(mis2);
		em.persist(mis3);
		em.getTransaction().commit();
		em.close();
	}
        
        @Test
	public void findAllMissions() {
		EntityManager em = emf.createEntityManager();
		List<Mission> missions = em.createQuery("SELECT p FROM Mission p",Mission.class).getResultList();
		em.close();
		
		Assert.assertEquals(missions.size(), 3);
	}
}
