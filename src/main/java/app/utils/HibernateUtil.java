package app.utils;

import app.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            configuration.addResource("hibernate.cfg.xml");
            //Aggiungi classi che crei
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Auto.class);
            configuration.addAnnotatedClass(Probl.class);
            configuration.addAnnotatedClass(Pren.class);
            configuration.addAnnotatedClass(Codicesc.class);
            configuration.addAnnotatedClass(Ruoli.class);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
