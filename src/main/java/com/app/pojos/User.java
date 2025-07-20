package com.app.pojos;

import javax.persistence.*;
import java.time.LocalDate;

// jpa compliant - Hibernate independent
// For Hibernate Pojo - classes need not be serializable, the primary key property should be serializable
@Entity // mandatory class level annotation -> telling jvm this has to be persistent
@Table(name = "users_tbl") // specifies table name
public class User {
    @Id // mandatory specify the primary key of an entity
//    @GeneratedValue // Hibernate chooses the default db specific strategy for automatic primary key generation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment constraint suitable for MySQL DB
    @Column(name = "user_id")
    private Integer userId;

    @Column(length = 20) // varchar(20)
    private String name;

    @Column(length = 20, unique = true) // varchar(20), unique constraint
    private String email;

    @Column(length = 15, nullable = false) // varchar(15), NOT NULL constraint
    private String password;

    @Enumerated(EnumType.STRING) // column type varchar(20)
    @Column(name = "user_role", length = 20)
    private UserRole userRole;

    @Transient // skips from persistent (no corresponding column)
    private String confirmPassword;

    @Column(name = "reg_amount")
    private double regAmount;

    @Column(name = "reg_date")
    private LocalDate regDate; // column type : date

    @Lob // column type blob : medium blob
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
                '}';
    }
}
