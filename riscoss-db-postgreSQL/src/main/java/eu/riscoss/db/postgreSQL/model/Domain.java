package eu.riscoss.db.postgreSQL.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "domain")
public class Domain {	
	@Id
	@Column(unique = true)
    private String domainName;
	@Column(unique = false)
    private Boolean isPrivate;
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;
	

/*	@OneToMany(mappedBy = "domainName", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles= new HashSet<UserRole>(0);

	public Set<UserRole> getUserRoles() {
	    return userRoles;
	}
	
	public void setUserRoles(Set<UserRole> userRoles)
	{
		this.userRoles = userRoles;		
	}
	*/
	
	/*
	@ManyToOne(cascade = CascadeType.ALL)
	private UserRole userRole;
	
	public UserRole getUserRole() {
	    return userRole;
	}
	
	public void setUserRole( UserRole userRole) {
	    this.userRole = userRole;
	}*/
	
	public Role getRole() {
	    return role;
	}
	
	public void setRole( Role role) {
	    this.role = role;
	}
	
    public Domain() { }
    
    public Domain(String domainName, Role defaultRole, Boolean isPrivate) { 
    	
    	this.domainName = domainName;
    	this.role = defaultRole;
    	//this.defaultRole = defaultRole;
    	this.isPrivate = isPrivate;
    }
    
  /*  public String getdefaultRole() {
        return defaultRole;
    }

    public void setdefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }*/

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    @Override
    public String toString() {
        return "UserRole [domainName=" + domainName + ", defaultRole=" + role.getRoleName() +  ", isPrivate=" + isPrivate + "]";
    }
}
