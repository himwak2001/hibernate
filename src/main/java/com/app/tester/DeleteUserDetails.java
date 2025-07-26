package com.app.tester;

import com.app.dao.UserDaoImple;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class DeleteUserDetails {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter User email to delete user details: ");
            // create dao instance n test API
            UserDaoImple dao = new UserDaoImple();
            System.out.println(dao.unsubscribeUser(sc.next()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

