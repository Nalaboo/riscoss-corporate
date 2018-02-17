package eu.riscoss.db.postgreSQL;
import org.hibernate.Session;
import eu.riscoss.db.postgreSQL.model.UserEntity;
import junit.framework.TestCase;

public class UserEntityTest extends TestCase{

	public static void main(String[] args) {
		
		testApp();
		
	}
	
	public static void testApp() {
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
}
