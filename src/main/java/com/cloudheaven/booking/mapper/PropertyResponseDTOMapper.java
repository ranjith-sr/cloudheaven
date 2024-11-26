package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.PropertyResponseDTO;
import com.cloudheaven.booking.model.property.Property;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PropertyResponseDTOMapper implements Function<Property, PropertyResponseDTO> {
    @Override
    public PropertyResponseDTO apply(Property property) {
        return PropertyResponseDTO.builder()
                .propertyName(property.getPropertyName())
                .propertyId(property.getPropertyId())
                .description(property.getDescription())
                .propertyType(property.getPropertyType())
                .payPerDay(property.getPayPerDay())
                .payPerNight(property.getPayPerNight())
                .payPerFullDay(property.getPayPerFullDay())
                .address(property.getAddress())
                .payments(property.getPropertyPaymentList())
                .build();
    }
}
