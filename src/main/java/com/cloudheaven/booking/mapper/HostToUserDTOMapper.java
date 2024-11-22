package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.model.user.Host;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class HostToUserDTOMapper implements Function<Host, UserDTO> {

    @Override
    public UserDTO apply(Host host) {
        return UserDTO.builder()
                .userId(host.getUserId())
                .name(host.getName())
                .email(host.getEmail())
                .mobileNo(host.getMobile_no())
                .gender(host.getGender())
                .dob(host.getDob())
                .createdAt(host.getCreatedAt())
                .build();
    }
}
