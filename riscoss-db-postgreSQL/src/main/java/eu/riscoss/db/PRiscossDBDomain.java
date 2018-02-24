package eu.riscoss.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class PRiscossDBDomain implements RiscossDBDomain{

	private static String url = "jdbc:postgresql://localhost:5432/riscoss-db-postgres";
	private static String user = "postgres";
	private static String pass = "admin";
 
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

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listDomains() {
		
		/*List<String> lDomains = new ArrayList <String>();
		String SQL = "SELECT domainname FROM userrole";
		try (Connection conn = PRiscossDBDomain_Connect();
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(SQL)) {
	        	 while (rs.next()) {
	        		 lDomains.add(rs.getString("domainname"));
	             }
	        	 return lDomains;
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }	*/    
		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void createDomain(String domainName) {
		/*String SQL = "INSERT INTO domain (domainname, defaultrole, ispublic) VALUES";
		try (Connection conn = PRiscossDBDomain_Connect();
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(SQL)) {

	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }	 */   		
	}

	public void deleteDomain(String domainName) {
		// TODO Auto-generated method stub
//buscar el dominio en la BD y entonces eliminarlo, solo con el nombre de dominio		
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRoleProperty(String role, String key, String value) {
		// TODO Auto-generated method stub
		
	}

	public String getRoleProperty(String role, String key, String def) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listUsers(String from, String max, String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> listPublicDomains() {
		//Dominios pblicos para ese usuario
		return null;
	}

	public void setPredefinedRole(String domain, String value) {
		// TODO Auto-generated method stub
		
	}

	public String getPredefinedRole(String domain) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createRole(String roleName) {
		// TODO Auto-generated method stub
		
	}

	public List<String> listDomains(String username) { //lista los domains de ese usuario
		/*List<String> lDomains = new ArrayList <String>();
		String SQL = "SELECT domainname FROM userrole";
		try (Connection conn = PRiscossDBDomain_Connect();
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(SQL)) {
	        	 while (rs.next()) {
	        		 lDomains.add(rs.getString("domainname"));
	             }
	        	 return lDomains;
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }	    */
		return null;
	}

	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	public SiteManager getSiteManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsDomain(String domain) {
		// TODO Auto-generated method stub
		//Hacer un select con el nombre pasdo por parametro. Si si se encuentra devolver true si no, false
		return false;
	}
 
    
	
}
