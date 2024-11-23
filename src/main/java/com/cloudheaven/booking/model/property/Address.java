package com.cloudheaven.booking.model.property;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Column(name = "apartment_number")
    private String apartmentNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private int postalCode;

}
