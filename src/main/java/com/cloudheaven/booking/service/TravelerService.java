package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.TravelerToUserDTOMapper;
import com.cloudheaven.booking.model.user.Traveler;
import com.cloudheaven.booking.model.user.UserType;
import com.cloudheaven.booking.repo.TravelerRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TravelerService {
    private final TravelerRepo travelerRepo;
    private final TravelerToUserDTOMapper travelerToUserDTOMapper;

    public TravelerService(TravelerRepo travelerRepo , TravelerToUserDTOMapper travelerToUserDTOMapper){
        this.travelerRepo = travelerRepo;
        this.travelerToUserDTOMapper = travelerToUserDTOMapper;
    }

    public List<UserDTO> getAllTravelers(){
        return travelerRepo.findAll()
                .stream()
                .map(travelerToUserDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO createTraveler(UserRegistrationDTO userRegisterDto){
        Traveler traveler = Traveler.builder()
                .name(userRegisterDto.name())
                .email(userRegisterDto.email())
                .password(userRegisterDto.password())
                .gender(userRegisterDto.gender())
                .mobile_no(userRegisterDto.mobileNo())
                .dob(userRegisterDto.dob())
                .account_type(UserType.TRAVELER)
                .createdAt(ZonedDateTime.now())
                .build();
        return travelerToUserDTOMapper.apply(travelerRepo.save(traveler));
    }

    public UserDTO getTravelerById(Long userId) throws ResourceNotFoundException{
        Traveler traveler = travelerRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        return travelerToUserDTOMapper.apply(traveler);
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException {
        Traveler traveler = travelerRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        travelerRepo.delete(traveler);
    }

    public void updateTraveler(Map<String, Object> userUpdateFields, Long userId) throws ResourceNotFoundException {
        Traveler traveler = travelerRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        List<String> fields = new ArrayList<>();
        userUpdateFields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Traveler.class , key);
            if(field != null){
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field , traveler , value);
            } else {
                fields.add(key);
            }
        });
        fields.forEach(key->{
            userUpdateFields.remove(key);
        });
        travelerRepo.save(traveler);
    }
}