package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.HostDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.exceptions.UserAlreadyExistsException;
import com.cloudheaven.booking.mapper.HostDTOMapper;
import com.cloudheaven.booking.model.user.Host;
import com.cloudheaven.booking.model.user.UserType;
import com.cloudheaven.booking.repo.HostRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final HostDTOMapper hostDTOMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public HostService(
            HostRepo hostRepo ,
            HostDTOMapper hostDTOMapper ,
            BCryptPasswordEncoder passwordEncoder
    ){
        this.hostRepo = hostRepo;
        this.hostDTOMapper = hostDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<HostDTO> getAllHosts(){
        return hostRepo.findAll()
                .stream()
                .map(hostDTOMapper)
                .collect(Collectors.toList());
    }

    public HostDTO createHost(UserRegistrationDTO userRegistrationDTO){

        if(hostRepo.countByEmail(userRegistrationDTO.email()) == 1)
            throw new UserAlreadyExistsException(userRegistrationDTO.email());

        Host host = Host.builder()
                .name(userRegistrationDTO.name())
                .email(userRegistrationDTO.email())
                .password(passwordEncoder.encode(userRegistrationDTO.password()))
                .gender(userRegistrationDTO.gender())
                .mobile_no(userRegistrationDTO.mobileNo())
                .dob(userRegistrationDTO.dob())
                .account_type(UserType.HOST)
                .createdAt(ZonedDateTime.now())
                .properties(new ArrayList<>())
                .build();
        return hostDTOMapper.apply(hostRepo.save(host));
    }

    public HostDTO getUserById(Long userId) throws ResourceNotFoundException{
        Host host = hostRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        return hostDTOMapper.apply(host);
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
