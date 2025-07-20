package com.app.tester;

import com.app.dao.UserDaoImple;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;

public class GetUserDetails {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Enter User Id to fetch user details: ");

            // create dao instance n test API
            UserDaoImple dao = new UserDaoImple();
            System.out.println(dao.getUserDetails(sc.nextInt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
