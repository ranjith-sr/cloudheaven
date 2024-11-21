package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.dto.UserRegisterDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.UserDTOMapper;
import com.cloudheaven.booking.model.user.User;
import com.cloudheaven.booking.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepo userRepo , UserDTOMapper userDTOMapper){
        this.userRepo = userRepo;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserDTO> getAllUsers(){
        return userRepo.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserRegisterDTO userRegisterDTO){
        User user = User.builder()
                .name(userRegisterDTO.name())
                .email(userRegisterDTO.email())
                .password(userRegisterDTO.password())
                .gender(userRegisterDTO.gender())
                .mobile_no(userRegisterDTO.mobileNo())
                .dob(userRegisterDTO.dob())
                .createdAt(ZonedDateTime.now())
                .build();
        return userDTOMapper.apply(userRepo.save(user));
    }

    public UserDTO getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return userDTOMapper.apply(user);
    }

    public void deleteUserById(Long userId){
        userRepo.deleteById(userId);
    }

    public void updateUser(Map<String , Object> userFields , Long userId) throws ResourceNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        List<String> fields = new ArrayList<>();
        userFields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(User.class , key);
            if(field != null){
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field , user , value);
            } else {
                fields.add(key);
            }
        });
        fields.forEach(key->{
            userFields.remove(key);
        });
        userRepo.save(user);
    }

}


