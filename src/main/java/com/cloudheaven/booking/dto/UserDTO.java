package com.cloudheaven.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Date;

@Builder
public record UserDTO(
        Long userId,
        String name ,
        String email,
        Long mobileNo ,
        @JsonFormat(pattern = "dd-MM-yyyy")
        Date dob,
        String gender,
        ZonedDateTime createdAt
) {

}