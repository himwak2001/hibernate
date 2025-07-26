package com.app.tester;

import com.app.dao.UserDaoImple;
import com.app.pojos.UserRole;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import static com.app.utils.HibernateUtils.getFactory;
import static java.time.LocalDate.parse;

public class GetSelectedPartialUserDetails {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in);) {
            System.out.println("Start, end Date and role");
            // create dao instance
            UserDaoImple dao = new UserDaoImple();
            System.out.println("User List ...");
            dao.getSelectedPartialUserDetails(parse(sc.next()), parse(sc.next()), UserRole.valueOf(sc.next().toUpperCase())).forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
 // select u from User u where u.email=:em and u.password:pswd;