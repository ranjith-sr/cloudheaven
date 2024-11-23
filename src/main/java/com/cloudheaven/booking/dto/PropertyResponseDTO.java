package com.cloudheaven.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PropertyResponseDTO extends PropertyRegisterDTO{
    private Long propertyId;
}