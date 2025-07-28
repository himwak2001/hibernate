package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable // mandatory
public class AadharCard {
    @Column(name = "card_number", unique = true, length = 30)
    private String cardNumber;
    @Column(name = "created_on")
    private LocalDate createdOn;
    @Column(length = 50)
    private String location;

    public AadharCard() {

    }

    public AadharCard(String cardNumber, LocalDate createdOn, String location) {
        this.cardNumber = cardNumber;
        this.createdOn = createdOn;
        this.location = location;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AadharCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", createdOn=" + createdOn +
                ", location='" + location + '\'' +
                '}';
    }
}
