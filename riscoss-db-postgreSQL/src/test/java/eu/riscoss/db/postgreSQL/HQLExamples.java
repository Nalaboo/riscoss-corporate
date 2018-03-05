package eu.riscoss.db.postgreSQL;

import java.util.List;

import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import eu.riscoss.db.postgreSQL.model.DomainEntity;

public class HQLExamples {
	
	public static void main(String[] args) {
		System.out.println("Object saved successfully.....!!");
       
		
        Session session = HibernateUtil.getSessionFactory().openSession();
		DomainEntity domain = new DomainEntity();
		domain.setDomainName("Laura's Domain");
		domain.setDefaultRole("admin");
		domain.setIsPublic(true);

		DomainEntity domain1 = new DomainEntity();
		domain1.setDomainName("Eva's Domain");
		domain1.setDefaultRole("admin");
		domain1.setIsPublic(true);
		
		Transaction tx = session.beginTransaction();
		session.save(domain);
		session.save(domain1);
		System.out.println("Object saved successfully.....!!");
		
        TypedQuery<DomainEntity> query = session.createQuery("from DomainEntity", DomainEntity.class);
        List<DomainEntity> domainList = query.getResultList();
			
		for (DomainEntity nameDomain : domainList)
		{
			System.out.println("Funcionaaaa  " + nameDomain.getDomainName());
		}
		
		tx.commit();
		session.close();
	/*	session.getTransaction().commit();
		
        TypedQuery<DomainEntity> query = session.createQuery("from DomainEntity", DomainEntity.class);
        List<DomainEntity> domainList = query.getResultList();
			
	//	assertTrue(domainList.size() >0);
		for (DomainEntity nameDomain : domainList)
		{
			System.out.println("Funcionaaaa  " + nameDomain);
		}
		
		*/
	/*	//HQL example - Get Employee with id
		query = session.createQuery("from Employee where id= :id");
		query.setLong("id", 3);
		Employee emp = (Employee) query.uniqueResult();
		System.out.println("Employee Name="+emp.getName()+", City="+emp.getAddress().getCity());
		
		//HQL pagination example
		query = session.createQuery("from Employee");
		query.setFirstResult(0); //starts with 0
		query.setFetchSize(2);
		empList = query.list();
		for(Employee emp4 : empList){
			System.out.println("Paginated Employees::"+emp4.getId()+","+emp4.getAddress().getCity());
		}
		
		//HQL Update Employee
		query = session.createQuery("update Employee set name= :name where id= :id");
		query.setParameter("name", "Pankaj Kumar");
		query.setLong("id", 1);
		int result = query.executeUpdate();
		System.out.println("Employee Update Status="+result);

		//HQL Delete Employee, we need to take care of foreign key constraints too
		query = session.createQuery("delete from Address where id= :id");
		query.setLong("id", 4);
		result = query.executeUpdate();
		System.out.println("Address Delete Status="+result);
		
		query = session.createQuery("delete from Employee where id= :id");
		query.setLong("id", 4);
		result = query.executeUpdate();
		System.out.println("Employee Delete Status="+result);
		
		//HQL Aggregate function examples
		query = session.createQuery("select sum(salary) from Employee");
		double sumSalary = (Double) query.uniqueResult();
		System.out.println("Sum of all Salaries= "+sumSalary);
		
		//HQL join examples
		query = session.createQuery("select e.name, a.city from Employee e "
				+ "INNER JOIN e.address a");
		List<Object[]> list = query.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}
		
		//HQL group by and like example
		query = session.createQuery("select e.name, sum(e.salary), count(e)"
				+ " from Employee e where e.name like '%i%' group by e.name");
		List<Object[]> groupList = query.list();
		for(Object[] arr : groupList){
			System.out.println(Arrays.toString(arr));
		}
		
		//HQL order by example
		query = session.createQuery("from Employee e order by e.id desc");
		empList = query.list();
		for(Employee emp3 : empList){
			System.out.println("ID Desc Order Employee::"+emp3.getId()+","+emp3.getAddress().getCity());
		}
		
		//rolling back to save the test data
		tx.rollback();*/
		
		//closing hibernate resources
		session.close();
	}
	

}
