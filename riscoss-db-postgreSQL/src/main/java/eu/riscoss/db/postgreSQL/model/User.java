package eu.riscoss.db.postgreSQL.model;

import javax.persistence.*;

@Entity
@Table(name = "[user]")
public class User {
	
	@Id
	@Column(unique = true)
    private String username;
	
	@Column(unique = false)
    private String password;
	
	@Column(unique = false)
    private String firstName;
	
	@Column(unique = false)
    private String lastName;
	
	@Column(unique = false)
    private Boolean isSuperAdmin;
	
	

    public User() { }
    
    public User(String domainname, String password, String firstName, String lastName, Boolean isSuperAdmin) { 
    	
    	this.username = domainname;
    	this.password = password;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.isSuperAdmin = isSuperAdmin;    
    }
    
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
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Boolean getIsSuperAdmin()
    {
        return isSuperAdmin;
    }
    public void setSuperAdmin(Boolean isSuperAdmin)
    {
        this.isSuperAdmin = isSuperAdmin;
    }
    
    @Override
    public String toString()
    {
        return "UserRole [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", isSuperAdmin=" + isSuperAdmin + "]";
    }
    
}
