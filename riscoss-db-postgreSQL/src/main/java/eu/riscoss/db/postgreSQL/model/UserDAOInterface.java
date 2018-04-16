package eu.riscoss.db.postgreSQL.model;
import java.util.List;

public interface UserDAOInterface {

	public void createUser(String username, String password, String firstName, String lastName);
	public Boolean checkPassword(String username, String passwordToCheck);
	public void save(User entity);
	public void update(User entity);
	public void delete(String userName);
	public Boolean isAdmin (String username);
	public List<String> listUsers(String pattern);
	public List<String> listUsers();
	
}
