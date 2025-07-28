package com.app.tester;

import com.app.dao.CourseDaoImplementation;
import com.app.pojos.Course;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class LaunchNewCourse {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter Course Details: title, startDate, endDate, fees, capacity");
            Course c = new Course(sc.next(), LocalDate.parse(sc.next()), LocalDate.parse(sc.next()), sc.nextDouble(), sc.nextInt());
            CourseDaoImplementation courseDaoImplementation = new CourseDaoImplementation();
            System.out.println(courseDaoImplementation.launchNewCourse(c));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
