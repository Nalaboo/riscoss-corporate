package eu.riscoss.db.postgreSQL;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.fasterxml.classmate.AnnotationConfiguration;

import eu.riscoss.db.postgreSQL.model.DomainEntity;
import eu.riscoss.db.postgreSQL.model.UserEntity;
import junit.framework.TestCase;

public class DomainEntityTest extends TestCase{

	private SessionFactory sessionFactory;
    private Session session = null;
	private PRiscossDBDomain pRisocssDBDomain;
	
	/*@Test
	public void testMethod1()
	{
		DomainEntity domain = new DomainEntity();
		domain.setDomainName("Laura's Domain");
		domain.setDefaultRole("admin");
		domain.setIsPublic(true);
		
		List<String> result = pRisocssDBDomain.listDomains();
	
		assertTrue(result.size() >0);
		for (String nameDomain : result)
		{
			assertEquals("Laura's Domain", nameDomain);
		}
	}*/
	
	@Test
	public void testMethod2()
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
 
		UserEntity user = new UserEntity();
		user.setFirstName("lola");
		user.setLastName("perez");
		user.setPassword("mememe");
		user.setUserName("lola");
		session.save(user);
 
		System.out.println(user);
		session.getTransaction().commit();
		session.close();	
		
	}
	
	
/*
    @Before
    public void before() {
     // setup the session factory
     AnnotationConfiguration configuration = new AnnotationConfiguration();
     configuration.addAnnotatedClass(SuperHero.class)
       .addAnnotatedClass(SuperPower.class)
       .addAnnotatedClass(SuperPowerType.class);
     configuration.setProperty("hibernate.dialect",
       "org.hibernate.dialect.H2Dialect");
     configuration.setProperty("hibernate.connection.driver_class",
       "org.h2.Driver");
     configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");
     configuration.setProperty("hibernate.hbm2ddl.auto", "create");
     sessionFactory = configuration.buildSessionFactory();
     session = sessionFactory.openSession();
    }*/
	
}
