package eu.riscoss.db.postgreSQL.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class UserDomainRoleID implements Serializable {
 
	@ManyToOne(cascade = CascadeType.ALL)
    private User user;
 
    @ManyToOne(cascade = CascadeType.ALL)
    private Domain domain;
 
    public UserDomainRoleID() {
    }
 
    public UserDomainRoleID(User user, Domain domain) {
        this.user = user;
        this.domain = domain;
    }
 
    public User getUsername() {
        return user;
    }
    
    public void setUsername(User user)
    {
    	this.user = user;
    }
 
    public Domain getDomainName() {
        return domain;
    }
    
    public void setDomainName(Domain domain)
    {
    	this.domain = domain;
    }
 
}
