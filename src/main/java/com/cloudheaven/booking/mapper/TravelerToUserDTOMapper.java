package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.model.user.Traveler;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TravelerToUserDTOMapper implements Function<Traveler, UserDTO> {

    @Override
    public UserDTO apply(Traveler traveler) {
        return UserDTO.builder()
                .userId(traveler.getUserId())
                .name(traveler.getName())
                .email(traveler.getEmail())
                .mobileNo(traveler.getMobile_no())
                .gender(traveler.getGender())
                .dob(traveler.getDob())
                .createdAt(traveler.getCreatedAt())
                .build();
    }
}
