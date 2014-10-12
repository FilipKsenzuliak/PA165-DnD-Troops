package cz.fi.muni.pa165;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cz.fi.muni.pa165.entity.Role;
import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.HeroDAO;
import cz.fi.muni.pa165.daoImpl.HeroDAOImpl;
import cz.fi.muni.pa165.entity.*;
import java.util.ArrayList;
import javax.persistence.PersistenceContext;

public class AppMain {


	public static void main(String[] args) throws SQLException {
	 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testingSetup");
    
        HeroDAOImpl impl;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        impl = new HeroDAOImpl();
        
        Hero hero = new Hero();
        hero.setAge(35L);
        hero.setRank(20L);
        hero.setName("Andrej");
        hero.setRace(Race.DWARF);
        
        Role role = new Role();
        role.setName("Wizard");
        role.setDescription("This is setup description.");
        role.setRoleLevel(4);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        hero.setRole(roles);
        
        Troop troop = new Troop();
        troop.setAmountOfMoney(100000);
        
        Mission mission = new Mission();
        mission.setName("Ryan.");
        mission.setObjective("Save private Ryan.");
        mission.setReward(10000);
        
        troop.setMission(mission);
        hero.setTroop(troop);
        
        em.getTransaction().begin();
        em.persist(hero);
        em.getTransaction().commit();  
        
        List<Hero> heroes = em.createQuery("SELECT h FROM Hero h").getResultList();
            System.out.println(heroes.get(0).toString());
            
                
        }

}
