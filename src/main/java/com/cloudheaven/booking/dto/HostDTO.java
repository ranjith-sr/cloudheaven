package com.cloudheaven.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class HostDTO extends UserDTO{

    List<PropertyResponseDTO> properties;

}
