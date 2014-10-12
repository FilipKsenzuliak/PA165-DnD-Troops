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
		new AnnotationConfigApplicationContext(DaoContext.class);
        }

}
