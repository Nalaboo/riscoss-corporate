package eu.riscoss.db.postgreSQL.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import eu.riscoss.db.postgreSQL.HibernateUtil;

/**
 * This class implements the functions related to the Domain.
*/
public class DomainService {
	private static DomainDAO domainDao;

	public DomainService()
	{
		domainDao = new DomainDAO();
	}
	
	/**
	*This method creates a Domain.
	*@param domainName the name of the Domain
	*/
	public void createDomain(String domainName)
	{
		Domain d = new Domain();
		d.setDomainName(domainName);
		save(d);
	}
	
	/**
	*This method saves a Domain.
	*@param entity the object Domain to be saved.
	*/
	public void save(Domain entity)
	{
		/*Session session = HibernateUtil.openCurrentSessionWithTransaction();
		domainDao.save(entity);
		HibernateUtil.closeCurrentSessionWithTransaction(session);*/
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(entity);		
		s.flush();
		tx.commit();
		s.close();	
	}
	
	/**
	*This method updates a Domain.
	*@param entity the object Domain to be updated.
	*/
	public void update(Domain entity)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.update(entity);		
		s.flush();
		tx.commit();
		s.close();
	}
	
	/**
	*This method deletes a Domain.
	*@param domainName the name of the Domain to be deleted.
	*/
	public void delete(String domainName)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Domain domain=  s.get(Domain.class, domainName );
		if(domain!=null)
		{
			Transaction tx = s.beginTransaction();
			s.delete(domain);
			s.flush();
			tx.commit();
		}
		s.close();
	}

	/**
	*This method is used to list all domains 
	*@return all the domainNames from the Domains 
	*/
	public List<String> findAll() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lDomainsName = new ArrayList<String>();	
	    TypedQuery<String> query = s.createQuery("select domainName from Domain", String.class);
        List<String> domainList = query.getResultList();
		if(domainList != null && domainList.size() > 0)
		{
			for (String domainName : domainList)
			{
				lDomainsName.add(domainName);
			}
		}
		s.close();
		return lDomainsName;
		/*
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<String> lDomainsName = null;	
	    TypedQuery<Domain> query = s.createQuery("from Domain", Domain.class);
	    List<Domain> domainList = query.getResultList();	
		for (Domain nameDomain : domainList)
		{
			lDomainsName.add(nameDomain.getDomainName());
		}
				//s.flush();
				s.close();	
		
		return lDomainsName;
	*/
		/*
		HibernateUtil.getSessionFactory().getCurrentSession();
		List<String> domains = domainDao.findAll();
		//HibernateUtil.getSessionFactory().close();
		return domains;*/
	}
	
	/**
	*This method is used to list all public domains 
	*@return all the domainNames from the Domains that have isPrivate to false
	*/
	public List<String> findAllPublic() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lDomainsName = new ArrayList<String>();
		TypedQuery<String> query = s.createQuery(" select domainName from Domain where isPrivate= :isPrivate", String.class);
        query.setParameter("isPrivate", false);
        List<String> domainList = query.getResultList();     
        if(domainList != null && domainList.size() > 0)
        {
			for (String nameDomain : domainList)
			{
				lDomainsName.add(nameDomain);
			}
        }
		s.close();		
		return lDomainsName;
	}
	
	/**
	*This method is used to check if a domain exists
	*@param domain the name of a Domain
	*@return If the domain exists, returns true
	*		 If the domain doesn't exists, returns false 
	*/
	public boolean existsDomain(String domain) {
		Session s = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Domain> queryExists = s.createQuery("from Domain where domainName= :domainName", Domain.class);
        queryExists.setParameter("domainName", domain);
        List<Domain> domainListExists = queryExists.getResultList();     
        Boolean existsDomain = false;
        if(domainListExists != null && domainListExists.size() > 0)
        {
        	existsDomain = true;
        }
        s.close();
        return existsDomain;
		
		
		/*HibernateUtil h = new HibernateUtil();
		HibernateUtil.getSessionFactory().getCurrentSession();
		Boolean existsDomain = domainDao.existsDomain(domain);
	//	HibernateUtil.getSessionFactory().close();
		return existsDomain;*/
	}
	
	/**
	*This method is used to get the PredefinedRole of a given Domain.
	*@param domain the name of a Domain
	*@return The DefaultRole of a Domain
	*/
	public String getPredefinedRole( String domain )
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		String predefinedRole = "";
		TypedQuery<Domain> query = s.createQuery("from Domain where domainName= :domainName", Domain.class);
		query.setParameter("domainName", domain);
	    List<Domain> domainListDefaultRole = query.getResultList();   
	    if(domainListDefaultRole != null && domainListDefaultRole.size() > 0)
	    {	
	    	predefinedRole =  domainListDefaultRole.get(0).getdefaultRole();
	    }
	    s.close();
	    return predefinedRole;
	}
	
	/**
	*This method is used to set the PredefinedRole to a given Domain.
	*In case the value is "private domain" isPrivate will be set to true
	*Otherwise, defaultRole will be updated
	*@param domainName the name of a Domain
	*@param value the name of the predefinedRole
	*/
	public void setPredefinedRole( String domainName, String value )
	{
		if(value != null && value.toLowerCase() != "private domain")
		{
		/*	Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = s.beginTransaction();
			TypedQuery<Domain> query = s.createQuery("update Domain set defaultRole =: defaultRole where domainName =: domainName", Domain.class);
			query.setParameter("defaultRole", value);
			query.setParameter("domainname", domain);
			query.executeUpdate();
			s.flush();
			tx.commit();
			s.close();*/
			Domain domain = new Domain();
			domain.setdefaultRole(value);
			domain.setDomainName(domainName);
			domain.setIsPrivate(false);
			update(domain);
		/*	Role role = new Role();
			role.setRoleName(value);
			Domain domain = new Domain();
			domain.setRole(role);
			domain.setDomainName(domainName);
			domain.setIsPrivate(false);
			update(domain);*/
		}
		else
		{
			/*Session s = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = s.beginTransaction();
			TypedQuery<Domain> query = s.createQuery("update Domain set isPrivate =: isPrivate where domainName =: domainName", Domain.class);
			query.setParameter("isPrivate", true);
			query.setParameter("domainName", domainName);
			query.executeUpdate();
			s.flush();
			tx.commit();
			s.close();
			*/
			String defaultRole = getPredefinedRole(domainName);
			Domain domain = new Domain();
			domain.setdefaultRole(defaultRole);
			domain.setDomainName(domainName);
			domain.setIsPrivate(true);
			update(domain);
			
			/*String defaultRole = getPredefinedRole(domainName);			
			Role role = new Role();
			role.setRoleName(defaultRole);
			Domain domain = new Domain();
			domain.setRole(role);
			domain.setDomainName(domainName);
			domain.setIsPrivate(true);
			update(domain);*/
		}
	}
	
	public DomainDAO domainDao()
	{
		return domainDao;
	}
	
}
