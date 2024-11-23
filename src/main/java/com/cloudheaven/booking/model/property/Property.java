package com.cloudheaven.booking.model.property;

import com.cloudheaven.booking.model.user.Host;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
}
