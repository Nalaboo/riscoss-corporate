package eu.riscoss.db.postgreSQL;
import java.util.List;

import eu.riscoss.db.postgreSQL.model.Domain;
import eu.riscoss.db.postgreSQL.model.DomainService;
import eu.riscoss.db.postgreSQL.model.Role;
import eu.riscoss.db.postgreSQL.model.RoleDAO;
import eu.riscoss.db.postgreSQL.model.User;
import eu.riscoss.db.postgreSQL.model.UserDAO;
import eu.riscoss.db.postgreSQL.model.UserDomainRoleID;
import eu.riscoss.db.postgreSQL.model.UserDomainRole;
import eu.riscoss.db.postgreSQL.model.UserDomainRoleDAO;

/**
 * This class tests the functions implemented in UserRoleDAO
*/
public class UserDomainRoleTest{

	public static void main(String[] args) 
	{
		List<String> lUsers;
		List<String> lDomains;
		List<String> lRoles;
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
		
		RoleDAO roleDAO = new RoleDAO();
		Role role1 = new Role();
		role1.setRoleName("admin");
		Role role2 = new Role();
		role2.setRoleName("cosumer");
		Role role3 = new Role();
		role3.setRoleName("modeler");
		Role role4 = new Role();
		role4.setRoleName("producer");
		roleDAO.save(role1);
		roleDAO.save(role2);
		roleDAO.save(role3);
		roleDAO.save(role4);
		
		DomainService domainService = new DomainService();
		Domain domain1 = new Domain("Maria's Domain", role1, true);
		Domain domain2 = new Domain("Elena's Domain", role1, false);
		Domain domain3 = new Domain("Joan's Domain", role2, false);
		Domain domain4 = new Domain("Marc's Domain", role3, true);
		domainService.save(domain1);
		domainService.save(domain2);
		domainService.save(domain3);		
		domainService.save(domain4);
		
		UserDomainRoleDAO userRoleDao = new UserDomainRoleDAO();
		UserDomainRole userRole1 = new UserDomainRole( new UserDomainRoleID(user1, domain1), role4);
		UserDomainRole userRole2 = new UserDomainRole(new UserDomainRoleID(user2, domain2), role1);
		UserDomainRole userRole3 = new UserDomainRole(new UserDomainRoleID(user3, domain1), role2);
		UserDomainRole userRole4 = new UserDomainRole(new UserDomainRoleID(user4, domain1), role4);
		UserDomainRole userRole5 = new UserDomainRole(new UserDomainRoleID(user1, domain2), role4);
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
		
		lDomains = userRoleDao.listDomains(user1.getUserName());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		lRoles = userRoleDao.listRoles(domain4.getDomainName());
		System.out.println("All Roles are :");
		if(lRoles != null && lRoles.size() > 0)
		for (String d : lRoles) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Roles - end ***");
		
		userRoleDao.delete(userRole1);
		
		lUsers = userRoleDao.listUsers("producer");
		System.out.println("All Users are :");
		if(lUsers != null && lUsers.size() > 0)
		for (String d : lUsers) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Users - end ***");
		
		userRoleDao.removeUserFromDomain (user1.getUserName(), domain1.getDomainName());
		
		lDomains = userRoleDao.listDomains(user1.getUserName());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		userRoleDao.setUserRole ("username_maria", "admin", "Maria's Domain");
		userRoleDao.setUserRole ("username_maria", "producer", "Marc's Domain");
		userRoleDao.setUserRole ("username_maria", "modeler", "Marc's Domain");
		userRoleDao.removeUserFromDomain (user1.getUserName(), "Joan's Domain");
		
		lDomains = userRoleDao.listDomains(user1.getUserName());
		System.out.println("All Domains are :");
		if(lDomains != null && lDomains.size() > 0)
		for (String d : lDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Domains - end ***");
		
		/*String getRole(String username, String domainname) */
		
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
