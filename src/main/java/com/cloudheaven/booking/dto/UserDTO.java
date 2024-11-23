package com.cloudheaven.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Date;

@SuperBuilder
@Data
public class UserDTO {

        private Long userId;
        private String name;
        private String email;
        private Long mobileNo;

        @JsonFormat(pattern = "dd-MM-yyyy")
        private Date dob;

        private String gender;
        private ZonedDateTime createdAt;
}