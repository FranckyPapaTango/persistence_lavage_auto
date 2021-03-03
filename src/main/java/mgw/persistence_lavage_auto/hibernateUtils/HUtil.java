package mgw.persistence_lavage_auto.hibernateUtils;
        
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

    public class HUtil {
        private static SessionFactory sessionFactory;
        private static ServiceRegistry serviceRegistry;

        public static SessionFactory createSessionFactory() {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }

        public static SessionFactory getSessionFactory() {
            return createSessionFactory();
        }
    }
