package eu.riscoss.db.postgreSQL.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import eu.riscoss.db.postgreSQL.HibernateUtil;
/**
 * This class implements the functions related to the UserRole.
*/
public class UserRoleDAO {
	
	/**
	*This method is used to list the users that have role as the parameter role
	*@param role the name of the role
	*@return the usernames from the Users that have this role
	*/
	public List<String> listUsers(String role) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lUsers = new ArrayList<String>();

        // create Criteria
       /* CriteriaQuery<UserRole> criteriaQuery = s.getCriteriaBuilder().createQuery(UserRole.class);
        criteriaQuery.from(UserRole.class);
        criteriaQuery.select("where ");
        criteriaQuery.distinct(true);
        List<UserRole> userRoleList = s.createQuery(criteriaQuery).getResultList();
        */
        
       /* CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.where(builder.equal(root.get("role"), role));
        query.distinct(true);
        Query<UserRole> q= s.createQuery(query);
        List<UserRole> userRoleList=q.getResultList();
        */
        CriteriaBuilder builder = s.getCriteriaBuilder();
     /*   CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root.get("username")).where(builder.equal(root.get("role"), role));
        query.distinct(true);
        TypedQuery<String> q= s.createQuery(query);
        List<String> userRoleList=q.getResultList();
        */
       /* EntityManager em;
        Metamodel m = em.getMetamodel();
        EntityType<UserRole> UserRole_ = m.entity(UserRole.class);
        */
      /*  CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.where(builder.equal(root.get("role"), role));
        query.select(root.get("username")).distinct(true);
        TypedQuery<String> q= s.createQuery(query);
        List<String> userRoleList=q.getResultList();*/
        
      /*  CriteriaQuery<String> criteria = builder.createQuery( String.class );
        Root<UserRole> personRoot = criteria.from( UserRole.class );
        criteria.select( personRoot.get( UserRole_.username ) );
        criteria.where( builder.equal( personRoot.get( UserRole_.role ), role ) );
        List<String> ages = em.createQuery( criteria ).getResultList();*/
        
        //Funciona ok -maven build
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<UserRole> root = criteriaQuery.from(UserRole.class);
        criteriaQuery.select(root.<String>get("username")).distinct(true);
        criteriaQuery.where(criteriaBuilder.equal(root.get("role"), role));

        Query<String> query = s.createQuery(criteriaQuery);

        List<String>userRoleList = (List<String>)query.getResultList();
        
	 /*   CriteriaBuilder cb = s.getCriteriaBuilder();
	    CriteriaQuery<String> cq = cb.createQuery(String.class);
	    Root<UserRole> root = cq.from(UserRole.class);
	    cq.select(root.get("username")).distinct(true);

	    //Here is the trick!
	    Predicate predicate = cb.equal(root.get("role"), role);

	    cq.where(predicate);

	    TypedQuery<String> query = s.createQuery(cq);
	    List<String> userRoleList= query.getResultList();*/
	    //
	    
	    /*
	    CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
	    CriteriaQuery<UserRole> criteriaQuery = criteriaBuilder.createQuery(UserRole.class);
	    Root<UserRole> root = criteriaQuery.from(UserRole.class);

	    Subquery<UserRole> subQuery = criteriaQuery.subquery(UserRole.class);
	    Root<UserRole> subRoot = subQuery.from(UserRole.class);

	    subQuery.where(criteriaBuilder.equal(subRoot.get("role"), role)).select(subRoot.get("username")).distinct(true);
	    criteriaQuery.setProjection(Projections.distinct(Projections.property("id")));
	    TypedQuery<UserRole> query = s.createQuery(criteriaQuery);
	    List<UserRole> userRoleList= query.getResultList();
	    */
        
	/*    
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<String> query = builder.createQuery(String.class);
	    Root<RuleVar> ruleVariableRoot = query.from(RuleVar.class);
	    query.select(ruleVariableRoot.get(RuleVar_.varType)).distinct(true);*/
	    
        /*ProjectionList projection = Projections.projectionList();
        criteriaQuery.distinct(true);
        Criteria criteria = session.createCriteria(Message.class);
        criteria.setProjection(Projections.distinct(Projections.property("msgFrom ")));
        List<String> msgFromList = criteria.list();
        
        Root<RuleVar> ruleVariableRoot = query.from(RuleVar.class);
        query.select(ruleVariableRoot.get(RuleVar_.varType)).distinct(true);
		
		TypedQuery<UserRole> query = s.createQuery("Distinct from UserRole where role= :role", UserRole.class);
        query.setParameter("role", role);*/
        
        //List<UserRole> userRoleList = query.getResultList();     
        
        if(userRoleList != null && userRoleList.size() > 0)
		for (String username : userRoleList)
		{
			lUsers.add(username);
		}
		s.close();		

		return lUsers;
	}
	
	/**
	*This method is used to list the domains that have an assigned User with the username given in the parameter username.
	*@param username the username of the User
	*@return the domains that have this username assigned
	*/
	public List<String> listDomains( String username )
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lDomainsName = new ArrayList<String>();

		TypedQuery<String> query = s.createQuery("select domainName from UserRole where username= :username", String.class);
        query.setParameter("username", username);
        List<String> domainNameList = query.getResultList();     
        
        if(domainNameList != null && domainNameList.size() > 0)
        {
    		for (String domainName : domainNameList)
    		{
    			lDomainsName.add(domainName);
    		}
        }
    	s.close();		

    	return lDomainsName;
	}

	/**
	*This method is used to list the roles that the users of a given domain have.
	*@param domainName the name of the Domain
	*@return the roles that the users of a given Domain have.
	*/ 
	public List<String> listRoles (String domainName)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lDomainroles = new ArrayList<String>();
		
	/*	CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root.get("role")).where(builder.equal(root.get("domainName"), domainName));
        query.distinct(true);
        Query<UserRole> q= s.createQuery(query);
        List<UserRole> userRoleList=q.getResultList();*/
       
		CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root.<String>get("role")).where(builder.equal(root.get("domainName"), domainName));
        query.distinct(true);
        Query<String> q= s.createQuery(query);
        List<String> userRoleList=q.getResultList();
		
		/*TypedQuery<UserRole> query = s.createQuery("from UserRole where domainName= :domainName", UserRole.class);
        query.setParameter("domainName", domainName);
        List<UserRole> userRoleList = query.getResultList();*/     
        
        if(userRoleList != null && userRoleList.size() > 0)
        {
        	for (String userRole : userRoleList)
    		{
    			lDomainroles.add(userRole);
    		}
        }
    	s.close();		

    	return lDomainroles;
	}
	
	/**
	*This method removes a User from a Domain.
	*@param username the username of the User
	*@param domainName the name of the Domain
	*/
	public void removeUserFromDomain (String username, String domainName)
	{
	/*	Session s = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<UserRole> query = s.createQuery("from UserRole where username= :username AND domainName= :domainName", UserRole.class);
		query.setParameter("username", username);
		query.setParameter("domainName", domainName);
        UserRole userRole = query.getSingleResult();     
		s.close();
        if(userRole != null)
        {
        	delete(userRole);
        }*/
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<UserRole> query = s.createQuery("from UserRole where username= :username AND domainName= :domainName", UserRole.class);
		query.setParameter("username", username);
		query.setParameter("domainName", domainName);
        List<UserRole> userRole = query.getResultList();     
		s.close();
        if(userRole != null && userRole.size() > 0)
        {
        	delete(userRole.get(0));
        }
	}
	
	/**
	*This method sets a role to a User-Domain.
	*In case the relation Role, User, Domain exists, an update of the value is made.
	*Otherwise, a relation Role, User, Domain is created using the predefinedRole of the Domain.
	*@param username the username of the User
	*@param newRole the role of the 
	*@param domainName the name of the Domain
	*/
	public void setUserRole (String username, String newRole, String domainName)
	{
		String actualRole = getRole(username, domainName);
		if(actualRole != null)
		{
			/*TypedQuery<UserRole> query = s.createQuery("update UserRole set role= :newrole where username= :username and domainName= :domainName", UserRole.class);
			query.setParameter("role", newRole);
			query.setParameter("username", username);
			query.setParameter("domainName", domainName);
			query.executeUpdate();
			*/
			UserRole userRole = new UserRole();
			userRole.setUsername(username);
			userRole.setRole(newRole);
			userRole.setDomainName(domainName);
			update(userRole);
		}
		else
		{
			DomainService domainDAO = new DomainService();
			String predefniedRoleDomain = domainDAO.getPredefinedRole(domainName);
			if(predefniedRoleDomain != null)
			{
				UserRole userRole = new UserRole();
				userRole.setDomainName(domainName);
				userRole.setRole(predefniedRoleDomain);
				userRole.setUsername(username);
				save(userRole);
			}
		}
	}
	
	/**
	*This method returns a role given a username and a domainName.
	*@param userrole the username of the User
	*@param domainName the name of the Domain
	*@return returns the role of the username-domainName relation
	*/
	public String getRole(String username, String domainName)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		String role = null;
		TypedQuery<String> query = s.createQuery("Select role from UserRole where username= :username and domainName= :domainName", String.class);
		query.setParameter("username", username);
		query.setParameter("domainName", domainName);
		List<String> userRole = query.getResultList();  
		if(userRole != null && userRole.size() > 0)
		{
			role = userRole.get(0);
		}
		s.close();

		return role;
	}
	
	/**
	*This method saves a UserRole.
	*@param entity the object UserRole to be saved.
	*/
	public void save(UserRole entity)
	{	
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(entity);		
		s.flush();
		tx.commit();
		s.close();	
	}
	
	/**
	*This method updates a UserRole.
	*@param entity the object UserRole to be updated.
	*/
	public void update(UserRole entity)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.update(entity);		
		s.flush();
		tx.commit();
		s.close();
	}
	
	/**
	*This method deletes a UserRole.
	*@param entity the object UserRole to be deleted.
	*/
	public void delete(UserRole userrole)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		if(userrole!=null)
		{
			Transaction tx = s.beginTransaction();
			s.delete(userrole);
			s.flush();
			tx.commit();
		}
		s.close();
	}
	
	
	
}
