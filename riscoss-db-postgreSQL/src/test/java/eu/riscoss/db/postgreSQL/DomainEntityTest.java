package eu.riscoss.db.postgreSQL;
import org.hibernate.Session;

import eu.riscoss.db.postgreSQL.model.DomainEntity;
import junit.framework.TestCase;

public class DomainEntityTest extends TestCase{

	public static void main(String[] args) {
		
		testApp();
		
	}
	
	public static void testApp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
		 
		DomainEntity domain = new DomainEntity();
		domain.setDomainName("Domain1");
		domain.setDefaultRole("admin");
		domain.setIsPublic(true);
		session.save(domain);
 
		System.out.println(domain);
		session.getTransaction().commit();
		session.close();
	}
}
