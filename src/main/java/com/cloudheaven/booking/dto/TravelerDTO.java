package com.cloudheaven.booking.dto;

import com.cloudheaven.booking.model.payment.PropertyPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class TravelerDTO extends UserDTO {

    Set<PropertyResponseDTO> wishList;
    List<PropertyPayment> payments;

}
