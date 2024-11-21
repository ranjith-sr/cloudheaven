package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.model.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .mobileNo(user.getMobile_no())
                .gender(user.getGender())
                .dob(user.getDob())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
