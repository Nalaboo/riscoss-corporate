package eu.riscoss.db.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "userrole")
public class UserRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = false)
    private String username;
	
	@Id
	@Column(unique = false)
    private String domainName;
	
	@Column(unique = false)
    private String role;
	
    public UserRole() { }
    
    public UserRole(String userName, String domainName, String role) 
    { 
    	this.username = userName;
    	this.domainName = domainName;
    	this.role = role;
    }
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getDomainName()
    {
        return domainName;
    }
    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }
    
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    
    @Override
    public String toString()
    {
        return "UserRole [username=" + username + ", domainName=" + domainName + ", role=" + role + "]";
    }
    
}

