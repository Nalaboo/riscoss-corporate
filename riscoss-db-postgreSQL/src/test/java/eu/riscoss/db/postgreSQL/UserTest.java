package eu.riscoss.db.postgreSQL;
import java.util.List;

import eu.riscoss.db.postgreSQL.model.User;
import eu.riscoss.db.postgreSQL.model.UserDAO;

/**
 * This class tests the functions implemented in UserDAO
*/
public class UserTest{

	public static void main(String[] args) {
		List<String> lUsers;

		UserDAO userDao = new UserDAO();
		User user1 = new User("username_maria", "mariaPassw", "Maria","Vives", false);
		User user2 = new User("username_laura", "lauraPassw", "Laura","Vives", false);
		User user3 = new User("username_carlos", "carlosPassw", "Carlos","Vives", false);
		User user4 = new User("username_Roberto", "RobertoPassw", "Roberto","Vives", false);
		User user5 = new User("admin", "admin", "admin","admin", false);
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);
		userDao.save(user5);
		
		lUsers = userDao.listUsers();
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		Boolean user4isAdmin = userDao.isAdmin(user4.getUserName());
		if(user4isAdmin)
		{
			System.out.println("*** Is admin ***" + userDao.isAdmin(user4.getUserName()));
		}
		else
		{
			System.out.println("*** Is not admin ***" + userDao.isAdmin(user4.getUserName()));
		}
		System.out.println("*** Is/Isn't Admin - end ***");
		
		Boolean user5isAdmin = userDao.isAdmin(user5.getUserName());
		if(user5isAdmin)
		{
			System.out.println("*** Is admin ***" + userDao.isAdmin(user5.getUserName()));
		}
		else
		{
			System.out.println("*** Is not admin ***" + userDao.isAdmin(user5.getUserName()));
		}
		System.out.println("*** Is/Isn't Admin - end ***");
		
		userDao.delete(user4.getUserName());
		userDao.delete(user2.getUserName());
		
		lUsers = userDao.listUsers();
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		user1.setSuperAdmin(true);
		userDao.update(user1);
		
		Boolean user1isAdmin = userDao.isAdmin(user1.getUserName());
		if(user1isAdmin)
		{
			System.out.println("*** Is admin ***" + userDao.isAdmin(user1.getUserName()));
		}
		else
		{
			System.out.println("*** Is not admin ***" + userDao.isAdmin(user1.getUserName()));
		}
		System.out.println("*** Is/Isn't Admin - end ***");
		
		lUsers = userDao.listUsers();
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		lUsers = userDao.listUsers("pepe");
		System.out.println("All Users similar to pepe are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** Similar to pepe - end ***");
		
		lUsers = userDao.listUsers("m");
		System.out.println("All Users similar to m are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** Similar to m - end ***");
		
		lUsers = userDao.listUsers("username");
		System.out.println("All Users similar to username are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** Similar to username - end ***");
		
		Boolean isSamePass = userDao.checkPassword(user1.getUserName(), user1.getPassword());
		if(isSamePass)
		{
			System.out.println("*** Is same password ***" + user1.getUserName());
		}
		else
		{
			System.out.println("*** Is not same password ***" + user1.getUserName());
		}
		System.out.println("*** Same password - end ***");
		
		Boolean isNotSamePass = userDao.checkPassword(user1.getUserName(), user5.getPassword());
		if(isNotSamePass)
		{
			System.out.println("*** Is same password ***" + user5.getUserName());
		}
		else
		{
			System.out.println("*** Is not same password ***" + user5.getUserName());
		}
		System.out.println("*** I not Same password - end ***");
		
	}
	
	public static void testApp() {
    /*    Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
 
		UserEntity user = new UserEntity();
		user.setFirstName("lola");
		user.setLastName("perez");
		user.setPassword("mememe");
		user.setUserName("lola");
		session.save(user);
 
		System.out.println(user);
		session.getTransaction().commit();
		session.close();*/
	}
		

		
}
