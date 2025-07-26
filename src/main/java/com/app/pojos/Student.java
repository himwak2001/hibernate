package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity {
    // StudentId (Inherited from BaseEntity), name, email + Course courseEnrolled
    @Column(length = 30)
    private String name;

    @Column(length = 30, unique = true)
    private String email;

    @ManyToOne // many to one multiplicity
    @JoinColumn(name = "course_id") // specifying foreign key column name
    private Course courseEnrolled;

    public Student() {
        System.out.println("in constructor of: " + getClass().getName());
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

    public Course getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(Course courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    @Override
    public String toString() {
        return "Student{" + "Student Id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courseEnrolled=" + courseEnrolled +
                '}';
    }
}
