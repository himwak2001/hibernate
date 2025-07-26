package com.app.tester;

import com.app.dao.UserDaoImple;
import org.hibernate.SessionFactory;

import static com.app.utils.HibernateUtils.getFactory;

public class GetAllUserDetails {
    public static void main(String[] args) {
        try (SessionFactory sf = getFactory()) {
            // create dao instance
            UserDaoImple dao = new UserDaoImple();
            System.out.println("User List ...");
            dao.getAllUserDetails().forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
