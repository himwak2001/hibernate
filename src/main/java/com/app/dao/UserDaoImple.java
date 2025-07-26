package com.app.dao;

import com.app.pojos.User;
import com.app.pojos.UserRole;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
        Transaction tx = session.beginTransaction(); // db connection is pooled out -- wrapped in the session object, L1 cache is created
        try {
            // org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
            Serializable userId = session.save(user); // userId : created and used by Hibernate based on auto generation strategy // user ref is added in the cache, state: PERSISTENT
            tx.commit(); // upon commit, hibernate perform "automatic dirty checking" : compare state of L1 cache with that of db: automatically DML will be fired (insert query)
            // close the connection -> so that pooled out connection return to the pool and L1 cache is destroyed
            message = "user registration successfully with ID: " + userId;
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

    @Override
    public List<User> getAllUserDetails() {
        List<User> users = null;
        String jpql = "select u from User u";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            // session -> create query -> getResultList (persistent list)
            users = session.createQuery(jpql, User.class).getResultList(); // no query cache -> n no of select query get fire
            // users: list of persistent entities
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return users; // users: list of detached entities
    }

    // NOT Recommended -> bypasses L1 cache i.e., L1 cache remain empty -> cascading not supported : can be use only on standalone table
    @Override
    public List<User> getSelectedUserDetails(LocalDate strt, LocalDate end, UserRole role) {
        List<User> users = null;
        String jpql = "select u from User u where u.regDate between :strtDate and :endDate and u.userRole=:rl";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            // session -> create query object -> set 3 named IN params
            users = session.createQuery(jpql, User.class).
                    setParameter("strtDate", strt)
                    .setParameter("endDate", end).setParameter("rl", role).getResultList();
            // users: list of persistent entities
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return users;
    }

    @Override
    public List<String> getSelectedUserNames(LocalDate strt, LocalDate end, UserRole rl) {
        List<String> usersName = null;
        String jpql = "select u.name from User u where u.regDate between :strtDate and :endDate and u.userRole=:rl";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            // session -> create query object -> set 3 named IN params
            usersName = session.createQuery(jpql, String.class).
                    setParameter("strtDate", strt)
                    .setParameter("endDate", end).setParameter("rl", rl).getResultList();
            // users: list of persistent entities
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return usersName;
    }

    @Override
    public List<User> getSelectedPartialUserDetails(LocalDate strt, LocalDate end, UserRole rl) {
        List<User> users = null;
        String jpql = "select new com.app.pojos.User(email, regAmount, regDate) from User u where u.regDate between :strtDate and :endDate and u.userRole=:rl";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            // session -> create query object -> set 3 named IN params
            users = session.createQuery(jpql, User.class).
                    setParameter("strtDate", strt)
                    .setParameter("endDate", end).setParameter("rl", rl).getResultList();
            // users: list of persistent entities
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return users;
    }

    @Override
    public String changePassword(int userId, String newPassword) {
        String mesg = "Password Updation Failed";
        User user = null;
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            user = session.get(User.class, userId); // int -> integer -> Serializable
            if (user != null) {
                // user: PERSISTENT (Part of L1 cache + has corresponding record in DB)
                // valid id
                user.setPassword(newPassword); // changing the state of PERSISTENT entity
                mesg = "User's password changed !!!";
            }
            tx.commit(); // Hibernate performs auto dirty checking : finds the state of the PERSISTENT entity changed -> DML (update), L1 cache is destroyed, cn returns to pool
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        user.setPassword("1234567890"); // user: DETACHED (No DML will be fired !!!)
        return mesg;
    }

    @Override
    public String applyDiscount(LocalDate regDate, double amount) {
        StringBuilder mesg = new StringBuilder("Total No of users updated: ");
        String jpql = "update User u set u.regAmount = u.regAmount - :disc where u.regDate < :date";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            int updateCount = session.createQuery(jpql).setParameter("disc", amount).setParameter("date", regDate).executeUpdate();
            mesg.append(updateCount);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return mesg.toString();
    }

    @Override
    public String unsubscribeUser(String email) {
        String mesg = "User un-subscription failed !!!";
        String jpql = "select u from User u where u.email = :email";
        // get session from the session factory
        Session session = getFactory().getCurrentSession();
        // begin transaction
        Transaction tx = session.beginTransaction();
        try {
            User user = session.createQuery(jpql, User.class).setParameter("email", email).getSingleResult();// if null -> NoResultException, no exception -> email is valid and user: PERSISTENT
            session.delete(user);// entity is simply marked for removal: REMOVED state
            tx.commit(); // Hibernate performs auto dirty checking -> DML (delete) -> L1 cache is destroyed, cn returns to pool -> user: TRANSIENT
            mesg = "user un-subscribed!!!";
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return mesg;
    }// user: doesn't exists (marked for garbage collection)
}
