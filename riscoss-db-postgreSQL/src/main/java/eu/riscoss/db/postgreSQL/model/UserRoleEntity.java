package eu.riscoss.db.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "userrole")
public class UserRoleEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
    private String username;
	
	@Id
	@Column(unique = true)
    private String domainname;
	
	@Column(unique = false)
    private String role;
	
    public UserRoleEntity() { }
    
    public String getUserName()
    {
        return username;
    }
    public void setUserName(String username)
    {
        this.username = username;
    }
    
    public String getDomainName()
    {
        return domainname;
    }
    public void setDomainName(String domainname)
    {
        this.domainname = domainname;
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
        return "UserRole [username=" + username + ", domainname=" + domainname + ", role=" + role + "]";
    }
    
}

