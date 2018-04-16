package eu.riscoss.db.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "userDomainRole")
public class UserDomainRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private UserDomainRoleID id;
	
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
