package com.cloudheaven.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class TravelerDTO extends UserDTO {

    //Sample Traveler Data
    String dummy;

}
