package eu.riscoss.db.postgreSQL;
import java.util.List;

import eu.riscoss.db.postgreSQL.model.Domain;
import eu.riscoss.db.postgreSQL.model.DomainService;
import eu.riscoss.db.postgreSQL.model.Role;
import eu.riscoss.db.postgreSQL.model.User;
import eu.riscoss.db.postgreSQL.model.UserDAO;
import eu.riscoss.db.postgreSQL.model.UserRole;
import eu.riscoss.db.postgreSQL.model.UserRoleDAO;

/**
 * This class tests the functions implemented in UserRoleDAO
*/
public class UserRoleTest{

	public static void main(String[] args) 
	{
		List<String> lUsers;
		List<String> lDomains;
		List<String> lRoles;
		createDomains();
		createUsers();
		UserRoleDAO userRoleDao = new UserRoleDAO();
		UserRole userRole1 = new UserRole("username_maria", "Maria's Domain", "producer");
		UserRole userRole2 = new UserRole("username_laura", "Elena's Domain", "admin");
		UserRole userRole3 = new UserRole("username_carlos", "Maria's Domain", "consumer");
		UserRole userRole4 = new UserRole("username_roberto", "Maria's Domain", "producer");
		UserRole userRole5 = new UserRole("username_maria", "Elena's Domain", "producer");
		userRoleDao.save(userRole1);
		userRoleDao.save(userRole2);
		userRoleDao.save(userRole3);		
		userRoleDao.save(userRole4);
		userRoleDao.save(userRole5);
		
		lUsers = userRoleDao.listUsers("producer");
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		lDomains = userRoleDao.listDomains(userRole1.getUsername());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		lRoles = userRoleDao.listRoles(userRole4.getDomainName());
		System.out.println("All Roles are :");
		if(lRoles != null && lRoles.size() > 0)
		for (String d : lRoles) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Roles - end ***");
		
		userRoleDao.delete(userRole4);
		
		lUsers = userRoleDao.listUsers("producer");
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		userRoleDao.removeUserFromDomain (userRole1.getUsername(), userRole1.getDomainName());
		
		lDomains = userRoleDao.listDomains(userRole1.getUsername());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		userRoleDao.setUserRole ("username_maria", "admin", "Maria's Domain");
		userRoleDao.setUserRole ("username_maria", "producer", "Marc's Domain");
		userRoleDao.setUserRole ("username_maria", "modeler", "Marc's Domain");
		userRoleDao.removeUserFromDomain (userRole1.getUsername(), "Joan's Domain");
		
		lDomains = userRoleDao.listDomains(userRole1.getUsername());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		/*String getRole(String username, String domainname) */
		
	}

	private static void createUsers() {
		UserDAO userDao = new UserDAO();
		User user1 = new User("username_maria", "mariaPassw", "Maria","Vives", false);
		User user2 = new User("username_laura", "lauraPassw", "Laura","Vives", false);
		User user3 = new User("username_carlos", "carlosPassw", "Carlos","Vives", false);
		User user4 = new User("username_roberto", "RobertoPassw", "Roberto","Vives", false);
		User user5 = new User("admin", "admin", "admin","admin", true);
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);
		userDao.save(user5);
	}

	private static void createDomains() {
		Role role1 = new Role();
		role1.setRoleName("admin");
		Role role2 = new Role();
		role2.setRoleName("cosumer");
		Role role3 = new Role();
		role3.setRoleName("modeler");
		DomainService domainService = new DomainService();
		/*Domain domain1 = new Domain("Maria's Domain", role1, true);
		Domain domain2 = new Domain("Elena's Domain", role1, false);
		Domain domain3 = new Domain("Joan's Domain", role2, false);
		Domain domain4 = new Domain("Marc's Domain", role3, true);*/
		
		Domain domain1 = new Domain("Maria's Domain", "admin", true);
		Domain domain2 = new Domain("Elena's Domain", "admin", false);
		Domain domain3 = new Domain("Joan's Domain", "cosumer", false);
		Domain domain4 = new Domain("Marc's Domain", "modeler", true);
		
		domainService.save(domain1);
		domainService.save(domain2);
		domainService.save(domain3);		
		domainService.save(domain4);
	}
	
	public static void testApp() {
    /*    Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
 
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setDomainName("Domain1");
		userRole.setUserName("user1");
		userRole.setRole("admin");
		session.save(userRole);
 
		System.out.println(userRole);
		session.getTransaction().commit();
		session.close();*/
	}

}
