package com.cloudheaven.booking.model.property;

import com.cloudheaven.booking.model.payment.PropertyPayment;
import com.cloudheaven.booking.model.user.Host;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "property_name")
    private String propertyName;

    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    private Double payPerDay ;
    private Double payPerNight ;
    private Double payPerFullDay;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    Host host;

    @OneToMany(mappedBy = "property")
    @JsonManagedReference
    List<PropertyPayment> propertyPaymentList;

}
