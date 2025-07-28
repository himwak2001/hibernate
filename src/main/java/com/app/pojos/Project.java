package com.app.pojos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project_tbl")
public class Project extends BaseEntity {
    @Column(name = "project_title", length = 30, unique = true)
    private String projectTitle;

    @Column(length = 200)
    private String technology;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    // many-to-many uni-directional between entities
    @ManyToMany // mandatory -> table will be created by hibernate with name provided by hibernate
    @JoinTable(name = "project_student", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "student_id")) // name of the third table and column names
    private Set<Student> students = new HashSet<>(); // to prevent NPE -> uses hashing algorithm

    public Project(){
        System.out.println("in ctor of: " + getClass().getName());
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectTitle='" + projectTitle + '\'' +
                ", technology='" + technology + '\'' +
                ", completionDate=" + completionDate +
                ", students=" + students +
                '}';
    }
}
