package com.app.pojos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

// jpa compliant - Hibernate independent
// For Hibernate Pojo - classes need not be serializable, the primary key property should be serializable
public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
    private String confirmPassword;
    private double regAmount;
    private LocalDate regDate;
    private byte[] image;

    // must supply a default constructor
    public User() {
        System.out.println("In user constructor");
    }

    public User(String name, String email, String password, UserRole userRole, String confirmPassword, double regAmount, LocalDate regDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.confirmPassword = confirmPassword;
        this.regAmount = regAmount;
        this.regDate = regDate;
    }

    // setters and getters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public double getRegAmount() {
        return regAmount;
    }

    public void setRegAmount(double regAmount) {
        this.regAmount = regAmount;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", regAmount=" + regAmount +
                ", regDate=" + regDate +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
