package eu.riscoss.db.postgreSQL.model;
import javax.persistence.*;

@Entity
@Table(name = "domain")
public class Domain {
	//private Role role;
	
	@Id
	@Column(unique = true)
    private String domainName;
	@Column(unique = false)
    private String defaultRole;
	@Column(unique = false)
    private Boolean isPrivate;
	/*@ManyToOne
	@JoinColumn(name = "roleName")
	
	public Role getRole() {
	    return role;
	}
	
	public void setRole( Role role) {
	    this.role = role;
	}*/
	
    public Domain() { }
    
    public Domain(String domainName, String defaultRole, Boolean isPrivate) { 
    	
    	this.domainName = domainName;
    	//this.role = defaultRole;
    	this.defaultRole = defaultRole;
    	this.isPrivate = isPrivate;
    }
    
    public String getdefaultRole() {
        return defaultRole;
    }

    public void setdefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

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
        return "UserRole [domainName=" + domainName + ", defaultRole=" + /*role +*/  ", isPrivate=" + isPrivate + "]";
    }
}
