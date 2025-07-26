package com.app.tester;

import com.app.dao.UserDaoImple;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class ApplyDiscount {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            // create dao instance
            UserDaoImple dao = new UserDaoImple();
            System.out.println("Enter date and discount: ");
            System.out.println(dao.applyDiscount(LocalDate.parse(sc.next()), sc.nextDouble()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
