package com.app.dao;

import com.app.pojos.AadharCard;
import com.app.pojos.Student;

import java.util.List;

public interface IStudentDao {
    // add a method to enroll the student to a course
    public String adminNewStudent(Student student, String courseTitle);

    // display all the student details from a specified course
    List<Student> getStudentByCourse(String courseTitle);

    // link aadhar card details to a student
    String linkAadharCard(int studentId, AadharCard card);
}
