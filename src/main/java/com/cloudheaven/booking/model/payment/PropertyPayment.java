package com.cloudheaven.booking.model.payment;


import com.cloudheaven.booking.model.property.Property;
import com.cloudheaven.booking.model.user.Traveler;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyPayment {

    @Id
    String paymentId;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    String token;
    Double amount;
    String currency;
    String paymentMethod;
    ZonedDateTime createdAt;
    String paymentIntent;
    Date checkIn;
    Date checkOut;

    @ManyToOne
    @JsonBackReference
    Traveler traveler;

    @ManyToOne
    @JsonBackReference
    Property property;
}
