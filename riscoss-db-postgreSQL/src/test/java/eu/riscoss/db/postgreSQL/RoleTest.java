package eu.riscoss.db.postgreSQL;

import java.util.List;

import eu.riscoss.db.postgreSQL.model.Role;
import eu.riscoss.db.postgreSQL.model.RoleDAO;
import eu.riscoss.db.postgreSQL.model.RoleDAOInterface;

/**
 * This class tests the functions implemented in RoleDAO
*/
public class RoleTest {
	
	public static void main(String[] args) {
		List<String> lRoles;
		RoleDAO roleDao = new RoleDAO();
		roleDao.createRole( "admin" );
		roleDao.createRole( "consumer" );
		roleDao.createRole( "modeler" );
		roleDao.createRole( "producer" );
		
		lRoles = roleDao.listRoles();
		System.out.println("All Roles are :");
		if(lRoles != null && lRoles.size() > 0)
		for (String d : lRoles) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Roles - end ***");
		
		roleDao.delete("consumer");
		
		lRoles = roleDao.listRoles();
		System.out.println("All Roles are :");
		if(lRoles != null && lRoles.size() > 0)
		for (String d : lRoles) {
			System.out.println("-" + d);
		}
		System.out.println("*** All Roles - end ***");
		
	}
}
