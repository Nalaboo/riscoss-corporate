package eu.riscoss.db.postgreSQL.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    	this.isPrivate = isPrivate;
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
        return "UserRole [domainName=" + domainName + ", defaultRole=" + role.getRoleName() +  ", isPrivate=" + isPrivate + "]";
    }
}
