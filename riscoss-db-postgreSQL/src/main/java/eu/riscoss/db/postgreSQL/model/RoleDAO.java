package eu.riscoss.db.postgreSQL.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import eu.riscoss.db.postgreSQL.HibernateUtil;
/**
 * This class implements the functions related to the Role.
*/
public class RoleDAO implements RoleDAOInterface{
	/**
	*This method saves a Role.
	*@param entity the object Role to be saved.
	*/
	public void save(Role entity)
	{	
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(entity);		
		s.flush();
		tx.commit();
		s.close();	
	}
	
	/**
	*This method updates a Role.
	*@param entity the object Role to be updated.
	*/
	public void update(Role entity)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.update(entity);		
		s.flush();
		tx.commit();
		s.close();
	}
	
	/**
	*This method deletes a Role.
	*@param roleName the name of the Role to be deleted.
	*/
	public void delete(String roleName)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		Role role =  s.get(Role.class, roleName );
		if(role!=null)
		{
			Transaction tx = s.beginTransaction();
			s.delete(role);
			s.flush();
			tx.commit();
		}
		s.close();
	}
	
	/**
	*This method creates a Role.
	*@param roleName the name of the Role
	*/
	public void createRole( String roleName )
	{	
		Role role = new Role();
		role.setRoleName(roleName);
		save(role);		
	}
	
	/**
	*This method is used to list all roles 
	*@return all the roleNames from the Roles 
	*/
	public List<String> listRoles() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<String> lRoleNames = new ArrayList<String>();	
	    TypedQuery<String> query = s.createQuery("select roleName from Role", String.class);
        List<String> roleList = query.getResultList();
        if(roleList != null && roleList.size() > 0)
        {
			for (String roleName : roleList)
			{
				lRoleNames.add(roleName);
			}
        }
		s.close();
		return lRoleNames;
	}
	
}
