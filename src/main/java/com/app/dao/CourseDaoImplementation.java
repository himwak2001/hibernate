package com.app.dao;

import com.app.pojos.Course;

import static com.app.utils.HibernateUtils.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseDaoImplementation implements ICourseDao {
    @Override
    public String launchNewCourse(Course course) {
        String msg = "launching new course failed !!!";
        // get the session from the SF
        Session session = getFactory().getCurrentSession();
        // begin the transaction
        Transaction txn = session.beginTransaction();
        try {
            session.persist(course);
            txn.commit();
            msg = "Launched Course: " + course.getTitle() + " with ID: " + course.getId();
        } catch (RuntimeException ex) {
            if (txn != null) {
                txn.rollback();
            }
            throw ex;
        }
        return msg;
    }
}
