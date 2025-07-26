package com.app.dao;

import com.app.pojos.User;
import com.app.pojos.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IUserDao {
    String registerUser(User user); // open session

    String registerUserWithGetCurrentSession(User user); // get current session

    // add a method to fetch user details from the supplied user id
    User getUserDetails(int userId);

    // add method to fetch list of all user details
    List<User> getAllUserDetails();

    // add method to fetch list of selected user details
    List<User> getSelectedUserDetails(LocalDate strt, LocalDate end, UserRole rl);

    // add method to fetch list of selected usernames
    List<String> getSelectedUserNames(LocalDate strt, LocalDate end, UserRole rl);

    // using constructor expression
    // add method to fetch list of partial user's details
    List<User> getSelectedPartialUserDetails(LocalDate strt, LocalDate end, UserRole rl);

    // add a method to change the user password
    String changePassword(int userId, String newPassword);

    // add a method to apply discount to some users - bulk update - use update query directly
    String applyDiscount(LocalDate regDate, double amount);

    // add a method to delete user details
    String unsubscribeUser(String email);
}
