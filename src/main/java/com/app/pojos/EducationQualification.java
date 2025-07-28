package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

// Student HAS-A Education Qualifications
// type (enum), year, % marks
@Embeddable
public class EducationQualification {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EduType type;
    private int year;
    private double marks;

    public EducationQualification() {
    }

    public EducationQualification(EduType type, int year, double marks) {
        this.type = type;
        this.year = year;
        this.marks = marks;
    }

    public EduType getType() {
        return type;
    }

    public void setType(EduType type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
