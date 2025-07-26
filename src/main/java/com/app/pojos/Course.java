package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "courses_tbl")
public class Course extends BaseEntity {
    // courseId, title, startDate, endDate, fees, Capacity
    @Column(length = 30, unique = true)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    private double fees;
    private int capacity;

    public Course() {
        System.out.println("in constructor of: " + getClass().getName());
    }

    public Course(String title, LocalDate startDate, LocalDate endDate, double fees, int capacity) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fees = fees;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Course{" + "Course Id=" + getId() +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fees=" + fees +
                ", capacity=" + capacity +
                '}';
    }
}
