package eu.riscoss.db.postgreSQL;

//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 * 	Helper class to provide a single implementation of a SessionFactory
 * 	to the application. When our application needs to perform a persistence
 * 	operation it will obtain a singleton SessionFactory from this Hibernate
 * 	util class. Using this singleton SessionFactory it can obtain a Session
 * 	and Session is basically the interface between our application and
 * 	Hibernate.  It is what we use to perform different persistence operations.*/
public class HibernateUtil {
   // private static final Logger logger = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static synchronized  SessionFactory buildSessionFactory() {
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
 
    /** Provide access to the singleton sessionFactory so we create a public method.
     *  This will provide the application with access to the singleton */
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