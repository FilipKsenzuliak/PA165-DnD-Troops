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

public class AppMain {


	public static void main(String[] args) throws SQLException {
		//The following line is here just to start up a in-memory database 
		new AnnotationConfigApplicationContext(DaoContext.class);
		
		System.out.println(" ****** STARTING PET STORE APPLICATOIN ****** ");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Calendar cal = Calendar.getInstance();
		
		printAllCages(emf);
                emf.close();
	}
	
	public static void printAllCages(EntityManagerFactory emf){
		System.out.println(" ********************************");
		System.out.println("        CAGES LIST      ");
		EntityManager em = emf.createEntityManager();
		List<Role> cages = em.createQuery("SELECT c from Cage c",Role.class).getResultList();
		
		for (Role c : cages) {
			System.out.println(c);
		}
		
		em.close();
		
	}

}
