package com.cloudheaven.booking.model.user;

import com.cloudheaven.booking.model.payment.PropertyPayment;
import com.cloudheaven.booking.model.property.Property;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("traveler")
@Table(name = "travelers")
@SuperBuilder
public class Traveler extends User{

    @ManyToMany
    Set<Property> wishList;

    @OneToMany(mappedBy = "traveler")
    @JsonManagedReference
    List<PropertyPayment> propertyPaymentList;

}
