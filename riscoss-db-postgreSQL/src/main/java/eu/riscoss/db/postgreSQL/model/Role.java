package eu.riscoss.db.postgreSQL.model;

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
	
	//private Set<Domain> domains;
	
	@Id
	@Column(unique = true, name = "roleName")
    private String roleName;
	
/*	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	public Set<Domain> getDomains() {
	    return domains;
	}*/
	
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
