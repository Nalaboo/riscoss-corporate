package eu.riscoss.db.postgreSQL.model;
import java.util.List;

public interface DomainDAOInterface {

	public void createDomain(String domainName);
	public void save(Domain entity);
	public void update(Domain entity);
	public void delete(String domainName);
	public List<String> findAll();
	public List<String> findAllPublic();
	public boolean existsDomain(String domain);
	public String getPredefinedRole( String domain);
	public void setPredefinedRole( String domainName, String value);
	
}
