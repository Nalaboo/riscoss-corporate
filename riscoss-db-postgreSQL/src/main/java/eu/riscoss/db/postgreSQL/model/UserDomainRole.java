package eu.riscoss.db.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "userrole")
public class UserDomainRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private UserDomainRoleID id;

	/*@Column(unique = false, name= "username")
	@Id private String username;
	
	@Column(unique = false, name = "domainName")
	@Id private String domainName;*/
	
	@ManyToOne()
	private Role role;
	
	public Role getRole() {
	    return role;
	}
	
	public void setRole( Role role) {
	    this.role = role;
	}
	
    public UserDomainRole() { }
    
    public UserDomainRole(UserDomainRoleID id, Role role) 
    { 
    	this.id = id;
    	this.role = role;
    }
    
   /* public UserRole(String userName, String domainName, Role role) 
    { 
    	this.username = userName;
    	this.domainName = domainName;
    	this.role = role;
    }*/

/*	public String getUsername()
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
    */
    
    
    public UserDomainRoleID getId() {
        return id;
    }
 
    public void setId(UserDomainRoleID id) {
        this.id = id;
    }
    
    
    @Override
    public String toString()
    {
        return "UserRole [username=" + id.getUsername() + ", domainName=" + id.getDomainName() + ", role=" + role + "]";
    }
    
}
