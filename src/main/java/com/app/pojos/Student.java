package com.app.pojos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity {
    // StudentId (Inherited from BaseEntity), name, email + Course courseEnrolled
    @Column(length = 30)
    private String name;

    @Column(length = 30, unique = true)
    private String email;

    @ManyToOne (fetch = FetchType.LAZY) // many to one multiplicity : mandatory (default fetch type = EAGER) --> changing to fetch lazily
    @JoinColumn(name = "course_id") // specifying foreign key column name
    private Course courseEnrolled; // In case on Lazy -> Hibernate create proxy/substitute of course object dynamically (runtime proxy)

    // one-to-one uni-directional association between Student (entity type) --> Aadhar Card (value type)
    @Embedded // optional annotation, added only for understanding
    private AadharCard aadharCard;

    // one-to-many uni-directional association between Student --> Education Qualification (composite value type)
    @ElementCollection // mandatory : to specify collection of composite value type (embeddable)
    @CollectionTable(name = "student_qualifications", joinColumns = @JoinColumn(name = "sid"))
    private List<EducationQualification> educationQualificationList = new ArrayList<>(); // separate table gets created and it will have FK referencing to the student_tbl


    public Student() {
        System.out.println("in constructor of: " + getClass().getName());
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
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

    public AadharCard getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(AadharCard aadharCard) {
        this.aadharCard = aadharCard;
    }

    @Override
    public String toString() {
        return "Student{" + "Student Id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email +
                '}';
    }
}
