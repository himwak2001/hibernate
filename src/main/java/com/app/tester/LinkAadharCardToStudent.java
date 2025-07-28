package com.app.tester;

import com.app.dao.StudentDaoImple;
import com.app.pojos.AadharCard;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class LinkAadharCardToStudent {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter student id");
            int sid = sc.nextInt();
            System.out.println("Enter card details: Card Number, LocalDate createdOn, String location");
            StudentDaoImple studentDaoImple = new StudentDaoImple();
            System.out.println(studentDaoImple.linkAadharCard(sid, new AadharCard(sc.next(), LocalDate.parse(sc.next()), sc.next())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
