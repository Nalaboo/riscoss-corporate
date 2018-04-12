package eu.riscoss.db.postgreSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import eu.riscoss.db.RiscossDBDomain;
import eu.riscoss.db.SiteManager;
import eu.riscoss.db.postgreSQL.model.DomainService;
import eu.riscoss.db.postgreSQL.model.RoleDAO;
import eu.riscoss.db.postgreSQL.model.UserDAO;
import eu.riscoss.db.postgreSQL.model.UserDomainRoleDAO;


public class PRiscossDBDomain implements RiscossDBDomain{

	private static String url = "jdbc:postgresql://localhost:5432/riscoss-db-postgres";
	private static String user = "postgres";
	private static String pass = "admin";
	private String username;
 
	
	
	/**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     * @throws java.sql.SQLException
     */
    public static Connection PRiscossDBDomain_Connect() throws SQLException {
    	Connection conexion = DriverManager.getConnection(url, user, pass);
        if (conexion != null){
        	System.out.print("Conexion establecida...");
        	return conexion;
        }
        else {
        	System.out.print("algo va mal");
        	
        }
        
        return null;
    }
    
    public PRiscossDBDomain( String addr, String username, String password) {
		
    	this.username = username;
		
	}
	
	public PRiscossDBDomain( String addr, byte[] tokenBytes ) {
		

		
	}

	public void init() {
		
	}

	public String getUsername() {
		return username;
	}

	public List<String> listDomains() {
		DomainService domainService = new DomainService();
		return domainService.findAll();
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void createDomain(String domainName) {
		DomainService domainService = new DomainService();
		domainService.createDomain(domainName);
  	}

	public void deleteDomain(String domainName) {
		DomainService domainDao = new DomainService();
		domainDao.delete(domainName);;
	}

	public String getRole() {
		String role = "";
		UserDAO userDAO = new UserDAO();
		if (userDAO.isAdmin(username))
		{
			role = "admin";
		}
		return role;
	}

	public void setRoleProperty(String role, String key, String value) {
		// TODO Auto-generated method stub
		
	}

	public String getRoleProperty(String role, String key, String def) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listRoles() {
		RoleDAO roleDao = new RoleDAO();
		return roleDao.listRoles();
	}

	public List<String> listUsers(String from, String max, String pattern) {
		UserDAO userDAO = new UserDAO();
		return userDAO.listUsers(pattern);
	}

	public List<String> listPublicDomains() {
		DomainService domainService = new DomainService();
		return domainService.findAllPublic();
	}

	public void setPredefinedRole(String domain, String value) {
		DomainService domainService = new DomainService();
		domainService.setPredefinedRole(domain, value);		
	}

	public String getPredefinedRole(String domain) {
		DomainService domainService = new DomainService();
		return domainService.getPredefinedRole(domain);
	}

	public void createRole(String roleName) {
		RoleDAO roleDao = new RoleDAO();
		roleDao.createRole(roleName);
	}

	public List<String> listDomains(String username) { 
		UserDomainRoleDAO userRoleDao = new UserDomainRoleDAO();
		return userRoleDao.listDomains(username);
	}

	public boolean isAdmin() {
		UserDAO userDAO = new UserDAO();
		return userDAO.isAdmin(username);
	}

	public SiteManager getSiteManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsDomain(String domain) {
		DomainService domainService = new DomainService();
		return domainService.existsDomain(domain);
	}
 
	public void deleteUser(String userName)//tiene que eliminar tambien de la tabla USerRole
	{
		UserDAO userDAO = new UserDAO();
		userDAO.delete(userName);
	}

	public void createUser(String userName, String password)
	{
		UserDAO userDAO = new UserDAO();
		userDAO.createUser(userName, password, "", "");
	}
	
}
