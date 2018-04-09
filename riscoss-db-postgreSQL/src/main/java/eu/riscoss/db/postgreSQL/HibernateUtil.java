package eu.riscoss.db.postgreSQL;

//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
   // private static final Logger logger = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        // read configuration and build session factory
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate_cfg.xml")
                        .build();

        try {
            return new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
      //      logger.error("cannot create sessionFactory", e);
            System.exit(1);
        }
        return null;

    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session openCurrentSessionWithTransaction()
    {
    	Session currentSession = getSessionFactory().openSession();
		currentSession.beginTransaction();
		return currentSession;
    }
    
    public static void closeCurrentSessionWithTransaction(Session session) {
    	session.getTransaction().commit();
	}
    
	public static void closeSessionFactory() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
	/*
	private static Session currentSession;
	private Transaction currentTransaction;
	
	public Session openCurrentSession()
	{
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	
	public Session openCurrentSessionWithTransaction()
	{
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}	
	
	public void closeCurrentSessionWithTransaction()
	{
		currentTransaction.commit();
		currentSession.close();		
	}
	
	public void closeCurrentSession()
	{
		currentSession.close();		
	}
	
	private static SessionFactory getSessionFactory()
	{
		/*Configuration configuration = new Configuration().configure("hibernate_cfg.xml");
		new StandardServiceRegistryBuilder().configure("hibernate_cfg.xml").build();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;*/
		
	/*	final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate_cfg.xml")
                        .build();

        try {
            return new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
          //  logger.error("cannot create sessionFactory", e);
            System.exit(1);
        }
        return null;
	}
	
	public static Session getCurrentSession() {	
		return currentSession;
	}
	
	public void setCurrentSession(Session currentSession)
	{
		this.currentSession = currentSession;
	}
	
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}
	
	public void setCurrentTransaction(Transaction currentTransaction)
	{
		this.currentTransaction = currentTransaction;
	}
    }q
	*/
