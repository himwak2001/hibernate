package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "address_tbl")
public class Address extends BaseEntity{
    @Column(length = 30)
    private String city;

    @Column(length = 30)
    private String country;

    @Column(length = 30, name = "zip_code")
    private String zipCode;

    @Column(length = 10, unique = true, name = "phone_no")
    private String phoneNo;

    // one to one uni directional association between entities
    @OneToOne // mandatory
    @JoinColumn(name = "student_id") // to specify the name of the FK column
    @MapsId // To tell hibernate : "student_id" column will be FK that references PK of student_tbl and will share the values from the same (Shared PK)
    private Student owner;

    public Address(){
        System.out.println("in ctor of:" + getClass().getName());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Student getOwner() {
        return owner;
    }

    public void setOwner(Student owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", owner=" + owner +
                '}';
    }
}
