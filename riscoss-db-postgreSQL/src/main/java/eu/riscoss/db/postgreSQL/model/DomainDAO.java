package eu.riscoss.db.postgreSQL.model;
import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import eu.riscoss.db.postgreSQL.HibernateUtil;

public class DomainDAO {
	
	public DomainDAO() {	
	}
	
	public void save(Domain entity)
	{
		
		
		
		
		/*
		SessionFactory factory = HibernateUtils.getSessionFactory();
	      Session session = factory.openSession();
	 
	      Query query = session.createQuery("from Book");
	 
	      List<Book> books = query.list();
	      session.flush();
	      session.close();*/
		 HibernateUtil.getSessionFactory().getCurrentSession().save(entity);		
	}	
	
	public void update(Domain entity)
	{
		HibernateUtil.getSessionFactory().getCurrentSession().update(entity);		
	}
	
	public void delete(String domainName)
	{		
		Domain domain=  HibernateUtil.getSessionFactory().getCurrentSession().get(Domain.class, domainName );
		if(domain!=null)
		{
			HibernateUtil.getSessionFactory().getCurrentSession().delete(domain);
		}
	}
	
	@SuppressWarnings("null")
	public List<String> findAll() {
		List<String> lDomainsName = null;	
        TypedQuery<Domain> query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from Domain", Domain.class);
        List<Domain> domainList = query.getResultList();
			
		for (Domain nameDomain : domainList)
		{
			lDomainsName.add(nameDomain.getDomainName());
		}
		return lDomainsName;
	}
	
/*	public List<String> listDomains(String username) { //lista los domains de ese usuario
        TypedQuery<Domain> query2 = session.createQuery("from Domain", Domain.class);
        List<Domain> domainList2 = query2.getResultList();
	}*/
	
	@SuppressWarnings("null")
	public List<String> findAllPublic() {
		List<String> lDomainsName = null;
		HibernateUtil h = new HibernateUtil();
		TypedQuery<Domain> query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from Domain where ispublic= :ispublic", Domain.class);
        query.setParameter("ispublic", true);
        List<Domain> domainList = query.getResultList();     
        
		for (Domain nameDomain : domainList)
		{
			lDomainsName.add(nameDomain.getDomainName());
		}
		return lDomainsName;
	}
	
	public boolean existsDomain(String domain) {
		HibernateUtil h = new HibernateUtil();
        TypedQuery<Domain> queryExists = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from Domain where domainname= :domainname", Domain.class);
        queryExists.setParameter("domainname", domain);
        List<Domain> domainListExists = queryExists.getResultList();     
        Boolean existsDomain = false;
        if(domainListExists.size() > 0)
        {
        	existsDomain = true;
        }
        return existsDomain;
	}
	
	public String getPredefinedRole( String domain )
	{
		HibernateUtil h = new HibernateUtil();
		String predefinedRole = "";
		TypedQuery<Domain> query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from Domain where domainname= :domainname", Domain.class);
		query.setParameter("domainname", domain);
	    List<Domain> domainListDefaultRole = query.getResultList();   
	    if(domainListDefaultRole.size() > 0)
	    {	
	    	//predefinedRole =  domainListDefaultRole.get(0).getDefaultRole();
	    }
	    return predefinedRole;
	}
	
}
