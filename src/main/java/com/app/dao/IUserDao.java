package com.app.dao;

import com.app.pojos.User;

import java.util.Optional;

public interface IUserDao {
    String registerUser(User user); // open session
    String registerUserWithGetCurrentSession(User user); // get current session
    // add a method to fetch user details from the supplied user id
    User getUserDetails(int userId);
}
