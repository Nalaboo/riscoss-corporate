package eu.riscoss.db.postgreSQL;
import org.hibernate.Session;
import eu.riscoss.db.postgreSQL.model.UserRoleEntity;
import junit.framework.TestCase;

public class UserRoleEntityTest extends TestCase{

	public static void main(String[] args) {
		
		testApp();
		
	}
	
	public static void testApp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
 
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setDomainName("Domain1");
		userRole.setUserName("user1");
		userRole.setRole("admin");
		session.save(userRole);
 
		System.out.println(userRole);
		session.getTransaction().commit();
		session.close();
	}

}
