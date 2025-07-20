package com.app.dao;

import com.app.pojos.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Optional;

import static com.app.utils.HibernateUtils.getFactory;

public class UserDaoImple implements IUserDao {
    // no data members, no constructor, no cleanup


    @Override
    public String registerUser(User user) {
        String message = "User reg failed...";
        // user: in java heap, state: TRANSIENT
        // get the session from session factory
        Session session = getFactory().openSession(); // jdbc connection will be obtained from this connection pool
        // begin a transaction
        Session session1 = getFactory().openSession();
        System.out.println(session == session1);
        Transaction tx = session.beginTransaction(); // db connection is pooled out -- wrapped in the session object, L1 cache is created
        System.out.println("Session is open: " + session.isOpen() + ", Connected to database: " + session.isConnected());
        try {
            // org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
            Serializable userId = session.save(user); // userId : created and used by Hibernate based on auto generation strategy // user ref is added in the cache, state: PERSISTENT
            tx.commit(); // upon commit, hibernate perform "automatic dirty checking" : compare state of L1 cache with that of db: automatically DML will be fired (insert query)
            message = "user registration successfully with ID: " + userId;
            System.out.println("Session is open: " + session.isOpen() + ", Connected to database: " + session.isConnected());// true and true
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            // close the connection -> so that pooled out connection return to the pool and L1 cache is destroyed
            if (session != null) {
                session.close();
            }
            System.out.println("Session is open: " + session.isOpen() + ", Connected to database: " + session.isConnected());// false and false
        }
        return message;
    }

    @Override
    public String registerUserWithGetCurrentSession(User user) {
        String message = "User reg failed...";
        // user: in java heap, state: TRANSIENT
        // get the session from session factory
        Session session = getFactory().getCurrentSession(); // jdbc connection will be obtained from this connection pool
        // begin a transaction
        Session session1 = getFactory().getCurrentSession();
        System.out.println(session == session1); // true
        Transaction tx = session.beginTransaction(); // db connection is pooled out -- wrapped in the session object, L1 cache is created
        System.out.println("Session is open: " + session.isOpen() + ", Connected to database: " + session.isConnected());
        try {
            // org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
            Serializable userId = session.save(user); // userId : created and used by Hibernate based on auto generation strategy // user ref is added in the cache, state: PERSISTENT
            tx.commit(); // upon commit, hibernate perform "automatic dirty checking" : compare state of L1 cache with that of db: automatically DML will be fired (insert query)
            // close the connection -> so that pooled out connection return to the pool and L1 cache is destroyed
            message = "user registration successfully with ID: " + userId;
            System.out.println("Session is open: " + session.isOpen() + ", Connected to database: " + session.isConnected());// false and false -> in case of get current session -> auto session closure
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return message;
    }

    @Override
    public User getUserDetails(int userId) {
        User user = null;
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            // get
            user = session.get(User.class, userId); // int -> auto-box into Integer(Serializable)
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return user;
    }
}
