package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.HostDTO;
import com.cloudheaven.booking.model.user.Host;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class HostDTOMapper implements Function<Host, HostDTO> {

    @Override
    public HostDTO apply(Host host) {
        return HostDTO.builder()
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
