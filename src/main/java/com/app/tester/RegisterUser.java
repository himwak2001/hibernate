package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import com.app.dao.UserDaoImple;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class RegisterUser {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter User Details: name, email, password, user role, confirm password, registration amount, registration date");
            // create transient POJO: exists in heap
            User user = new User(sc.next(), sc.next(), sc.next(), UserRole.valueOf(sc.next().toUpperCase()), sc.next(), sc.nextDouble(), LocalDate.parse(sc.next()));

            // create dao instance n test API
            UserDaoImple dao = new UserDaoImple();
            System.out.println(dao.registerUser(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
