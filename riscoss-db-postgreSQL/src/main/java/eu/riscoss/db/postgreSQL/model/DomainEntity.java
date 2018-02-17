package eu.riscoss.db.postgreSQL.model;
import javax.persistence.*;

@Entity
@Table(name = "domain")
public class DomainEntity {
	
	@Id
	@Column(unique = true)
    private String domainname;
	@Column(unique = true)
    private String defaultrole;
	@Column(unique = true)
    private Boolean ispublic;

    public DomainEntity() { }

    public String getDomainName() {
        return domainname;
    }

    public void setDomainName(String domainname) {
        this.domainname = domainname;
    }

    public String getDefaultRole() {
        return defaultrole;
    }

    public void setDefaultRole(String defaultrole) {
        this.defaultrole = defaultrole;
    }
    
    public Boolean getIsPublic() {
        return ispublic;
    }

    public void setIsPublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }
    
    @Override
    public String toString() {
        return String.format("name: %s, role: %s", getDomainName(), getDefaultRole());
    }
}
