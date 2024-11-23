package com.cloudheaven.booking.mapper;

import com.cloudheaven.booking.dto.HostDTO;
import com.cloudheaven.booking.model.user.Host;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class HostDTOMapper implements Function<Host, HostDTO> {
    private final PropertyResponseDTOMapper propertyResponseDTOMapper;
    public HostDTOMapper(PropertyResponseDTOMapper propertyResponseDTOMapper){
        this.propertyResponseDTOMapper = propertyResponseDTOMapper;
    }

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
                .properties(
                        host.getProperties()
                                .stream()
                                .map(propertyResponseDTOMapper)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
