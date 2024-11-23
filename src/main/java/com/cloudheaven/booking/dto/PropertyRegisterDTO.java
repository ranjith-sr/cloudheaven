package com.cloudheaven.booking.dto;

import com.cloudheaven.booking.model.property.Address;
import com.cloudheaven.booking.model.property.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRegisterDTO {
    private String propertyName ;
    private String description ;
    private PropertyType propertyType ;
    private Double payPerDay ;
    private Double payPerNight ;
    private Double payPerFullDay;
    private Address address;
}
