package eu.riscoss.db.postgreSQL.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class  Role {	
	@Id
	@Column(unique = true, name = "roleName")
    private String roleName;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private Set<Domain> domains= new HashSet<Domain>(0);
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private Set<UserDomainRole> userDomainRoles= new HashSet<UserDomainRole>(0);

	public Set<UserDomainRole> getUserRoles() {
	    return userDomainRoles;
	}
	
	public void setUserRoles(Set<UserDomainRole> userDomainRoles)
	{
		this.userDomainRoles = userDomainRoles;		
	}
	
	public Set<Domain> getDomains() {
	    return domains;
	}
	
	public void setDomains(Set<Domain> domains)
	{
		this.domains = domains;		
	}
	
    public Role() { }
    
    public Role(String roleName) 
    { 
    	this.roleName = roleName;	
    }
	
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String role)
    {
        this.roleName = role;
    }
    
    @Override
    public String toString()
    {
        return "Role [roleName=" + roleName + "]";
    }

}
