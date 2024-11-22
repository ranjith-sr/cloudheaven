package com.cloudheaven.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

@NotNull
public record UserRegistrationDTO(

        @NotNull
        String email,

        @NotNull
        String password ,

        @NotNull
        String name ,

        @NotNull
        Long mobileNo ,

        @NotNull
        @JsonFormat(pattern = "dd-MM-yyyy")
        @Past(message = "Date Of Birth should be in past")
        Date dob,

        @NotNull
        String gender
){
}
