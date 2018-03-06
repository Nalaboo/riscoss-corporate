package eu.riscoss.db.postgreSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;
import org.hibernate.Session;

import eu.riscoss.db.RiscossDBDomain;
import eu.riscoss.db.SiteManager;
import eu.riscoss.db.postgreSQL.model.DomainEntity;


public class PRiscossDBDomain implements RiscossDBDomain{

	private static String url = "jdbc:postgresql://localhost:5432/riscoss-db-postgres";
	private static String user = "postgres";
	private static String pass = "admin";
	private List<String> lstDomainName;
 
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
		lstDomainName = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
		//List<DomainEntity> lstDomains = (List<DomainEntity>) session.createCriteria(DomainEntity.class).list();			
       
        TypedQuery<DomainEntity> query = session.createQuery("FROM domain", DomainEntity.class);
        List<DomainEntity> domainList = query.getResultList();
        session.close();
        
     /*   TypedQuery<DomainEntity> q = session.createQuery("from Domain", DomainEntity.class);
        List<DomainEntity> cats = q.getResultList();*/
        
        
        for(DomainEntity domain: domainList)
        {
        	lstDomainName.add(domain.getDomainName());
    		//System.out.println("Domains name: " +  domain.getDomainName());
        }
        
        return lstDomainName;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void createDomain(String domainName) {
		DomainEntity domain = new DomainEntity();
		domain.setDomainName(domainName);
		domain.setDefaultRole("");
		domain.setIsPublic(true);
  	}

	public void deleteDomain(String domainName) {
		// TODO Auto-generated method stub
//buscar el dominio en la BD y entonces eliminarlo, solo con el nombre de dominio		
    /*    Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery("Delete");
		query.executeUpdate();*/
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
		
		//Domain tirthal = (Domain) session.createQuery("from com.tirthal.learning.mapping.component.Employee employee where employee.firstName = 'Tirthal'").uniqueResult();

		return false;
	}
 
    
	
}
