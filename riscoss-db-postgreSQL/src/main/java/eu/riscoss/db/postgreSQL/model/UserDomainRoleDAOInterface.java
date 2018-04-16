package eu.riscoss.db.postgreSQL.model;
import java.util.List;

public interface UserDomainRoleDAOInterface {

	public List<String> listUsers(String role);
	public List<String> listDomains( String username );
	public List<String> listRoles (String domainName);
	public void removeUserFromDomain (String username, String domainName);
	public void setUserRole (String username, String newRole, String domainName);
	public String getRole(String username, String domainName);
	public void save(UserDomainRole entity);
	public void update(UserDomainRole entity);
	public void delete(UserDomainRole userrole);
	
}
