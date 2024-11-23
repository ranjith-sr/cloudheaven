package com.cloudheaven.booking.dto;

import com.cloudheaven.booking.model.property.PropertyType;
import lombok.Builder;

@Builder
public record PropertyUpdateRequest(
        String propertyName ,
        String description ,
        PropertyType propertyType ,
        Double payPerDay ,
        Double payPerNight ,
        Double payPerFullDay ,
        String apartmentNumber,
        String street ,
        String city ,
        String state ,
        String country ,
        Integer postalCode
) {

}
