package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.HostToUserDTOMapper;
import com.cloudheaven.booking.model.user.Host;
import com.cloudheaven.booking.model.user.UserType;
import com.cloudheaven.booking.repo.HostRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HostService {
    private final HostRepo hostRepo;
    private final HostToUserDTOMapper hostToUserDTOMapper;

    public HostService(HostRepo hostRepo , HostToUserDTOMapper hostToUserDTOMapper){
        this.hostRepo = hostRepo;
        this.hostToUserDTOMapper = hostToUserDTOMapper;
    }

    public List<UserDTO> getAllHosts(){
        return hostRepo.findAll()
                .stream()
                .map(hostToUserDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO createHost(UserRegistrationDTO userRegistrationDTO){
        Host host = Host.builder()
                .name(userRegistrationDTO.name())
                .email(userRegistrationDTO.email())
                .password(userRegistrationDTO.password())
                .gender(userRegistrationDTO.gender())
                .mobile_no(userRegistrationDTO.mobileNo())
                .dob(userRegistrationDTO.dob())
                .account_type(UserType.HOST)
                .createdAt(ZonedDateTime.now())
                .build();
        return hostToUserDTOMapper.apply(hostRepo.save(host));
    }

    public UserDTO getUserById(Long userId) throws ResourceNotFoundException{
        Host host = hostRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        return hostToUserDTOMapper.apply(host);
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException {
        Host host = hostRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        hostRepo.delete(host);
    }

    public void updateUser(Map<String, Object> userUpdateFields, Long userId) throws ResourceNotFoundException {
        Host user = hostRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        List<String> fields = new ArrayList<>();
        userUpdateFields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Host.class , key);
            if(field != null){
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field , user , value);
            } else {
                fields.add(key);
            }
        });
        fields.forEach(key->{
            userUpdateFields.remove(key);
        });
        hostRepo.save(user);
    }
}
