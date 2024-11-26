package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.TravelerDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.TravelerDTOMapper;
import com.cloudheaven.booking.model.user.Traveler;
import com.cloudheaven.booking.model.user.UserType;
import com.cloudheaven.booking.repo.TravelerRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TravelerService {
    private final TravelerRepo travelerRepo;
    private final TravelerDTOMapper travelerDTOMapper;

    public TravelerService(TravelerRepo travelerRepo , TravelerDTOMapper travelerDTOMapper){
        this.travelerRepo = travelerRepo;
        this.travelerDTOMapper = travelerDTOMapper;
    }

    public List<TravelerDTO> getAllTravelers(){
        return travelerRepo.findAll()
                .stream()
                .map(travelerDTOMapper)
                .collect(Collectors.toList());
    }

    public TravelerDTO createTraveler(UserRegistrationDTO userRegisterDto){
        Traveler traveler = Traveler.builder()
                .name(userRegisterDto.name())
                .email(userRegisterDto.email())
                .password(userRegisterDto.password())
                .gender(userRegisterDto.gender())
                .mobile_no(userRegisterDto.mobileNo())
                .dob(userRegisterDto.dob())
                .account_type(UserType.TRAVELER)
                .createdAt(ZonedDateTime.now())
                .wishList(Collections.emptySet())
                .build();
        return travelerDTOMapper.apply(travelerRepo.save(traveler));
    }

    public TravelerDTO getTravelerById(Long userId) throws ResourceNotFoundException{
        Traveler traveler = travelerRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        return travelerDTOMapper.apply(traveler);
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