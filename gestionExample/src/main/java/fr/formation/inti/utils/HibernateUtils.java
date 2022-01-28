package fr.formation.inti.utils;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

	private static final Log log = LogFactory.getLog(HibernateUtils.class);
	private static final SessionFactory sessionFacory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		// Create the ServiceRegistry from hibernate.cfg.xml
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		// Create a metadata sources using the specified service registry.
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		
		return metadata.getSessionFactoryBuilder().build();
	}
	
	public static SessionFactory getSessionFacory() {
		log.debug("creation de la session Facoty");
		return sessionFacory;
	}
	
	public static void close() {
		getSessionFacory().close();
	}
}
