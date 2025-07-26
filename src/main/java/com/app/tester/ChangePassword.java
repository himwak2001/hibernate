package com.app.tester;

import com.app.dao.UserDaoImple;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class ChangePassword {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            // create dao instance
            UserDaoImple dao = new UserDaoImple();
            System.out.println("Enter user id and new password");
            System.out.println(dao.changePassword(sc.nextInt(), sc.next()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
