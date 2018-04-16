package eu.riscoss.db.postgreSQL.model;

import java.util.List;

public interface RoleDAOInterface {

	public void save(Role entity);
	public void update(Role entity);
	public void delete(String roleName);
	public void createRole( String roleName );
	public List<String> listRoles();
	
}
