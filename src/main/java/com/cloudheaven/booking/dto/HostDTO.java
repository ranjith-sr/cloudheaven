package com.cloudheaven.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class HostDTO extends UserDTO{

    //Sample HostData
    String dummy;

}
