package com.app.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

// bootstraping hibernate application
public class HibernateUtils {
    private static SessionFactory factory;

    // how to ensure creation of singleton instance of the session factory ? Singleton and Eager
    static {
        System.out.println("In the static initializer block");
        // booting hibernate using the hibernate.cfg.xml and building the session factory
        factory = new Configuration().configure().buildSessionFactory();
    }

    // getter for SessionFactory, return only single/same instance
    public static SessionFactory getFactory() {
        return factory;
    }
}
