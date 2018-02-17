package eu.riscoss.db.postgreSQL.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@Column(unique = true)
    private String username;
	
	@Column(unique = false)
    private String password;
	
	@Column(unique = false)
    private String firstname;
	
	@Column(unique = false)
    private String lastname;

    public UserEntity() { }
    
    public String getUserName()
    {
        return username;
    }
    public void setUserName(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getFirstName()
    {
        return firstname;
    }
    public void setFirstName(String firstname)
    {
        this.firstname = firstname;
    }
    
    public String getLastName()
    {
        return lastname;
    }
    public void setLastName(String lastname)
    {
        this.lastname = lastname;
    }

    @Override
    public String toString()
    {
        return "UserRole [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }
    
}
