package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.dto.UserRegisterDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserRegisterDTO userRegisterDTO){
        return userService.createUser(userRegisterDTO);
    }

    @GetMapping("/{user-id}")
    public UserDTO getUserById(@PathVariable("user-id") Long userId) throws ResourceNotFoundException {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUserById(userId);
        HashMap<String,String> response = new HashMap<>();
        response.put("id" , userId.toString());
        response.put("status" , "User Deleted Successfully");
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PatchMapping("/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateUser(@PathVariable("user-id") Long userId , @RequestBody Map<String , Object> userUpdateFields) throws ResourceNotFoundException {
        userService.updateUser(userUpdateFields , userId);
        userUpdateFields.put("status" , "Field Updated Successfully");
        return new ResponseEntity<>(userUpdateFields , HttpStatus.OK);
    }

}
