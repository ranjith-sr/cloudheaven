package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.TravelerDTO;
import com.cloudheaven.booking.model.user.Traveler;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TravelerDTOMapper implements Function<Traveler, TravelerDTO> {

    @Override
    public TravelerDTO apply(Traveler traveler) {
        return TravelerDTO.builder()
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
