package com.app.dao;

import com.app.pojos.AadharCard;
import com.app.pojos.Course;
import com.app.pojos.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.app.utils.HibernateUtils.getFactory;

public class StudentDaoImple implements IStudentDao {
    @Override
    public String adminNewStudent(Student student, String courseTitle) {
        String jpql = "select c from Course c where c.title = :title";
        String msg = "Student admission failed !!!";
        // get the session from the SF
        Session session = getFactory().getCurrentSession();
        // begin the transaction
        Transaction txn = session.beginTransaction();
        try {
            Course c = session.createQuery(jpql, Course.class).setParameter("title", courseTitle).getSingleResult();
            // course: PERSISTENT, student: TRANSIENT : till now no link between
            // entities
            // establish the link from student --> course (setter)
            student.setCourseEnrolled(c);
            session.persist(student);
            txn.commit();
            msg = "Student: " + student.getName() + " get enrolled in course " + courseTitle + " roll no " + student.getId();
        } catch (RuntimeException ex) {
            if (txn != null) {
                txn.rollback();
            }
            throw ex;
        }
        return msg;
    }

    @Override
    public List<Student> getStudentByCourse(String courseTitle) {
        List<Student> students = null;
//        String jpql = "select s from Student s where s.courseEnrolled.title = :title"; // cross join
        String jpql = "select s from Student s join fetch s.courseEnrolled c where c.title = :title"; // inner join - fetch : when we need student and course both - both data will be loaded - single query to fetch both the data
        // get the session from the SF
        Session session = getFactory().getCurrentSession();
        // begin the transaction
        Transaction txn = session.beginTransaction();
        try {
            students = session.createQuery(jpql, Student.class).setParameter("title", courseTitle).getResultList();
//            System.out.println("Within the session scope: " + students.get(0).getCourseEnrolled());
            txn.commit();
        } catch (RuntimeException ex) {
            if (txn != null) {
                txn.rollback();
            }
            throw ex;
        }
        return students;
    }

    @Override
    public String linkAadharCard(int studentId, AadharCard card) {
        String msg = "Linking aadhar card failed !!!";
        // get the session from the SF
        Session session = getFactory().getCurrentSession();
        // begin the transaction
        Transaction txn = session.beginTransaction();
        try {
            // get student details by id
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                // student : PERSISTENT
                student.setAadharCard(card);
                msg = "Linked aadhar card to Student: " + student.getName();
            }
            txn.commit();
        } catch (RuntimeException ex) {
            if (txn != null) {
                txn.rollback();
            }
            throw ex;
        }
        return msg;
    }
}
