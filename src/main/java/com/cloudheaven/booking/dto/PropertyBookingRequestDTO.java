package com.cloudheaven.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyBookingRequestDTO{

        Long propertyId ;
        Integer numOfGuest;

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date checkIn ;

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date checkOut ;

}
