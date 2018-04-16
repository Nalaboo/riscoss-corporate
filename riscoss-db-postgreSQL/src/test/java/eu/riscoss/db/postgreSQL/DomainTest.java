package eu.riscoss.db.postgreSQL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import eu.riscoss.db.postgreSQL.model.Domain;
import eu.riscoss.db.postgreSQL.model.DomainDAO;
import eu.riscoss.db.postgreSQL.model.Role;
import eu.riscoss.db.postgreSQL.model.RoleDAO;
/**
 * This class tests the functions implemented in DomainDAO
*/
public class DomainTest {
	
	public static void main(String[] args) {
		List<String> lAllDomains;
		List<String> lAllPublicDomains;

		DomainDAO domainDAO = new DomainDAO();
		RoleDAO roleDAO = new RoleDAO();
		Role role1 = new Role();
		role1.setRoleName("admin");
		Role role2 = new Role();
		role2.setRoleName("cosumer");
		Role role3 = new Role();
		role3.setRoleName("modeler");
		Domain domain1 = new Domain("Maria's Domain", role1, true);
		Domain domain2 = new Domain("Elena's Domain", role1, false);
		Domain domain3 = new Domain("Joan's Domain", role2, false);
		Domain domain4 = new Domain("Marc's Domain", role3, true);
		
		
		/*Domain domain1 = new Domain("Maria's Domain", "admin", true);
		Domain domain2 = new Domain("Elena's Domain", "admin", false);
		Domain domain3 = new Domain("Joan's Domain", "cosumer", false);
		Domain domain4 = new Domain("Marc's Domain", "modeler", true);*/
		domainDAO.save(domain1);
		domainDAO.save(domain2);
		domainDAO.save(domain3);		
		domainDAO.save(domain4);
		
		/*Set<Domain> domains = new HashSet<Domain>();
		domains.add(domain1);
		domains.add(domain2);
		role1.setDomains(domains);
		roleDAO.save(role1);
		Set<Domain> domains2 = new HashSet<Domain>();
		domains2.add(domain2);
		role2.setDomains(domains2);
		roleDAO.save(role2);
		Set<Domain> domains3 = new HashSet<Domain>();
		domains3.add(domain3);
		role2.setDomains(domains3);
		roleDAO.save(role3);*/
		
		
		lAllDomains = domainDAO.findAll();
		System.out.println("All domains are :");
		if(lAllDomains != null && lAllDomains.size() > 0)
		for (String d : lAllDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All domains - end ***");
		
		lAllPublicDomains = domainDAO.findAllPublic();
		System.out.println("All public domains are :");
		if(lAllPublicDomains != null && lAllPublicDomains.size() > 0)
		for (String d : lAllPublicDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All public domains - end ***");
		
		
		Boolean existsDomain = domainDAO.existsDomain(domain3.getDomainName());
		if(existsDomain)
		{
			System.out.println("*** Domain exists ***" + domain3.getDomainName());
		}
		else
		{
			System.out.println("*** Domain does not exist ***" + domain3.getDomainName());
		}
		System.out.println("*** Domain exists/Doesn't Exist - end ***");
		
		lAllDomains = domainDAO.findAll();
		System.out.println("All domains are :");
		if(lAllDomains != null && lAllDomains.size() > 0)
		for (String d : lAllDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All domains - end ***");
		
		String predefinedRole = domainDAO.getPredefinedRole(domain4.getDomainName());
		System.out.println("Predefined role of domain :" + domain4.getDomainName());
		System.out.println( predefinedRole + " *** Predefined role of domain : " + domain4.getDomainName() + "- end ***");

		
		domainDAO.delete(domain1.getDomainName());
		
		lAllDomains = domainDAO.findAll();
		System.out.println("All domains are :");
		if(lAllDomains != null && lAllDomains.size() > 0)
		for (String d : lAllDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All domains - end ***");
		
		lAllPublicDomains = domainDAO.findAllPublic();
		System.out.println("All public domains are :");
		if(lAllPublicDomains != null && lAllPublicDomains.size() > 0)
		for (String d : lAllPublicDomains) {
			System.out.println("-" + d);
		}
		System.out.println("*** All public domains - end ***");
		
     /*   Session session = HibernateUtil.getSessionFactory().openSession();
		DomainEntity domain = new DomainEntity();
		domain.setDomainName("Laura's Domain");
		domain.setDefaultRole("admin");
		domain.setIsPublic(true);

		// public void createDomain( String domainName ); 
		DomainEntity domain1 = new DomainEntity();
		domain1.setDomainName("Eva's Domain");
		domain1.setDefaultRole("admin");
		domain1.setIsPublic(true);
		
		DomainEntity domain2 = new DomainEntity();
		domain2.setDomainName("Sarah's Domain");
		domain2.setDefaultRole("admin");
		domain2.setIsPublic(false);
		
		Transaction tx = session.beginTransaction();
		session.save(domain);
		session.save(domain1);
		session.save(domain2);
		System.out.println("Object saved successfully.....!!");
		
        TypedQuery<DomainEntity> query = session.createQuery("from DomainEntity", DomainEntity.class);
        List<DomainEntity> domainList = query.getResultList();
			
		for (DomainEntity nameDomain : domainList)
		{
			System.out.println("Funcionaaaa  " + nameDomain.getDomainName());
		}
		
		// public void deleteDomain( String domainName );
		 DomainEntity customer1=session.get(DomainEntity.class, "Laura's Domain" );
        if(customer1!=null){
           session.delete(customer1);
           System.out.println("Customer 1 is deleted");
        }
        
        // public List<String> listDomains();
        TypedQuery<DomainEntity> query2 = session.createQuery("from DomainEntity", DomainEntity.class);
        List<DomainEntity> domainList2 = query2.getResultList();
			
		for (DomainEntity nameDomain : domainList2)
		{
			System.out.println("Laura deleted  " + nameDomain.getDomainName());
		}
        
		// public List<String> listPublicDomains();
        TypedQuery<DomainEntity> queryPublic = session.createQuery("from DomainEntity where ispublic= :ispublic", DomainEntity.class);
        queryPublic.setParameter("ispublic", true);
        List<DomainEntity> domainListPublic = queryPublic.getResultList();     
        
		for (DomainEntity nameDomain : domainListPublic)
		{
			System.out.println("Public domains " + nameDomain.getDomainName());
		}
		
		// public boolean existsDomain( String domain );
        TypedQuery<DomainEntity> queryExists = session.createQuery("from DomainEntity where domainname= :domainname", DomainEntity.class);
        queryExists.setParameter("domainname", "Sarah's");
        List<DomainEntity> domainListExists = queryExists.getResultList();     
        Boolean existsDomain = false;
        if(domainListExists.size() > 0)
        	existsDomain = true;

			System.out.println("Exists domains " + existsDomain);
				
			
		// 	public String getPredefinedRole( String domain );
	    @SuppressWarnings("unchecked")
		TypedQuery<String> queryDefaultRole = session.createQuery("Select d.defaultrole from DomainEntity d where domainname= :domainname");
	    queryDefaultRole.setParameter("domainname", "Sarah's Domain");
	    List<String> domainListDefaultRole = queryDefaultRole.getResultList();   
	    if(domainListDefaultRole.size() > 0)
		System.out.println("Default role " + domainListDefaultRole.get(0));

	    
		
		tx.commit();
		session.close();*/
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
		//session.close();
	}
	

}
