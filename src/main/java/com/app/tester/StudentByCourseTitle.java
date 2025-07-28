package com.app.tester;

import com.app.dao.StudentDaoImple;
import com.app.pojos.Student;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class StudentByCourseTitle {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter course title");
            StudentDaoImple studentDaoImple = new StudentDaoImple();
            studentDaoImple.getStudentByCourse(sc.next()).forEach(s -> System.out.println(s.getEmail() + " " + s.getCourseEnrolled().getTitle()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
