package eu.riscoss.db.postgreSQL.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import eu.riscoss.db.postgreSQL.HibernateUtil;
/**
 * This class implements the functions related to the User.
*/
public class UserDAO {
	
	/**
	*This method creates a User.
	*In case the username = admin then variable isSuperAdmin is changed to true.
	*@param username the username of the User
	*@param password the password of the User
	*@param firstName the firstName of the User
	*@param lastName the lastName of the User
	*/
	public void createUser(String username, String password, String firstName, String lastName)
	{	
		User user = new User();
		user.setUserName(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(DigestUtils.md2Hex(password));
		user.setSuperAdmin(false);
		if(username.toLowerCase() == "admin")
		{
			user.setSuperAdmin(true);
		}
		save(user);
	}
	
	/**
	*This method checks if a given password is the same as the User password.
	*@param username the username of the User
	*@param passwordToCheck the password to be compared with the User password
	*@return If the passwords are the same, it returns true.
	*		 If the passwords aren't the same, it returns false.
	*/
	public Boolean checkPassword(String username, String passwordToCheck)
	{
	/*	Boolean isEqual = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
	    TypedQuery<String> query = s.createQuery("select password from User where username= :username", String.class);
	    query.setParameter("username", username);
        String password = query.getSingleResult();
		s.close();
		
		String encryptedpasswordToCheck = DigestUtils.md2Hex(passwordToCheck);
		if(encryptedpasswordToCheck == password)
		{
			isEqual = true;
		}
		return isEqual;
		*/
		Boolean isEqual = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
	    TypedQuery<String> query = s.createQuery("select password from User where username= :username", String.class);
	    query.setParameter("username", username);
        List<String> lPasswords = query.getResultList();
		s.close();
		if(lPasswords != null && lPasswords.size() > 0)
		{
			String encryptedpasswordToCheck = DigestUtils.md5Hex(passwordToCheck);
			if(encryptedpasswordToCheck == lPasswords.get(0))
			{
				isEqual = true;
			}
		}
		return isEqual;
	}
	
	/**
	*This method saves a User.
	*@param entity the object User to be saved.
	*/
	public void save(User entity)
	{	
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(entity);		
		s.flush();
		tx.commit();
		s.close();	
	}
	
	/**
	*This method updates a User.
	*@param entity the object User to be updated.
	*/
	public void update(User entity)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.update(entity);		
		s.flush();
		tx.commit();
		s.close();
	}
	
	/**
	*This method deletes a User.
	*@param entity the object User to be deleted.
	*/
	public void delete(String userName)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		User user=  s.get(User.class, userName );
		if(user!=null)
		{
			Transaction tx = s.beginTransaction();
			s.delete(user);
			s.flush();
			tx.commit();
		}
		s.close();
	}
	
	/**
	*This method returns if the given username is SuperAdmin.
	*@param username the username of the User
	*@return If the given username is SuperAdmin, it returns true.
	*        If the given username isn't SuperAdmin, it returns false.
	*/
	public Boolean isAdmin (String username)
	{
	/*	Boolean isAdmin = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
	    TypedQuery<User> query = s.createQuery("from User where username= :username", User.class);
	    query.setParameter("username", username);
        User user = query.getSingleResult();
		if(user.getIsSuperAdmin())
		{
			isAdmin = true;
		}
		s.close();
		return isAdmin;
		*/
		
		Boolean isAdmin = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
	    TypedQuery<User> query = s.createQuery("from User where username= :username", User.class);
	    query.setParameter("username", username);
        List<User> lUsers = query.getResultList();
        if(lUsers != null && lUsers.size() > 0)
        {
			if(lUsers.get(0).getIsSuperAdmin())
			{
				isAdmin = true;
			}
        }
		s.close();
		return isAdmin;
	}
	
	/**
	*This method is used to list the users which have a username similar to the given pattern.
	*@param pattern The pattern that wants to be searched
	*@return the usersnames from the Users that are similar to the given pattern 
	*/
	public List<String> listUsers(String pattern)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();	
		List<String> lUsersName = new ArrayList<String>();	
	    TypedQuery<String> query = s.createQuery("Select username from User u where u.username like :pattern", String.class);
	    query.setParameter("pattern", "%" + pattern + "%" );
        List<String> usersNameList = query.getResultList();
        if(usersNameList != null && usersNameList.size() > 0)
		{
        	for (String userName : usersNameList)
			{
				lUsersName.add(userName);
			}
        }
		s.close();
		return lUsersName;
	}
	
	/**
	*This method is used to list all users 
	*@return all the usersnames from the Users 
	*/
	public List<String> listUsers() {
		Session s = HibernateUtil.getSessionFactory().openSession();	
		List<String> lUsersName = new ArrayList<String>();	
	    TypedQuery<User> query = s.createQuery("from User", User.class);
        List<User> usersList = query.getResultList();
		if(usersList != null && usersList.size() > 0)
		{
			for (User user : usersList)
			{
				lUsersName.add(user.getUserName());
			}
		}
		s.close();
		return lUsersName;
	}
	/*
	public List<String> listUsers(String role) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lDomainsName = new ArrayList<String>();

		TypedQuery<Domain> query = s.createQuery("from UserEntity where ispublic= :ispublic", Domain.class);
        query.setParameter("ispublic", true);
        List<Domain> domainList = query.getResultList();     
        
        if(domainList != null && domainList.size() > 0)
		for (Domain nameDomain : domainList)
		{
			lDomainsName.add(nameDomain.getDomainName());
		}
		s.close();		

		return lDomainsName;
	}
	
	public boolean isAdmin(String userName) {
		Session s = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Domain> queryExists = s.createQuery("from DomainEntity where domainname= :domainname", Domain.class);
        queryExists.setParameter("domainname", domain);
        List<Domain> domainListExists = queryExists.getResultList();     
        Boolean existsDomain = false;
        if(domainListExists.size() > 0)
        {
        	existsDomain = true;
        }
        s.close();
        return existsDomain;
	}*/

	
	
}
