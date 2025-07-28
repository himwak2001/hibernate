package com.app.tester;

import com.app.dao.CourseDaoImplementation;
import com.app.dao.StudentDaoImple;
import com.app.pojos.Course;
import com.app.pojos.Student;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class StudentAdmission {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter Student Details: name and email");
            Student student = new Student(sc.next(), sc.next());
            System.out.println("Enter course title");
            StudentDaoImple studentDaoImple = new StudentDaoImple();
            System.out.println(studentDaoImple.adminNewStudent(student, sc.next()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
