package com.cloudheaven.booking.dto;

import com.cloudheaven.booking.model.payment.PropertyPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PropertyResponseDTO extends PropertyRegisterDTO{
    private Long propertyId;
    private List<PropertyPayment> payments;
}