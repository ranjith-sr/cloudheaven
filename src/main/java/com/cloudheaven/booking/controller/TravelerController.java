package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.UserDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.service.TravelerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/travelers")
public class TravelerController {
    private final TravelerService travelerService;

    public TravelerController(TravelerService travelerService){
        this.travelerService = travelerService;
    }

    @GetMapping
    public List<UserDTO> getAllTravellers(){
        return travelerService.getAllTravelers();
    }

    @PostMapping
    public UserDTO createTraveler(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return travelerService.createTraveler(userRegistrationDTO);
    }

    @GetMapping("/{user-id}")
    public UserDTO getTravelerById(@PathVariable("user-id") Long id) throws ResourceNotFoundException {
        return travelerService.getTravelerById(id);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteTraveler(@PathVariable("user-id") Long id) throws ResourceNotFoundException {
        travelerService.deleteUser(id);
        HashMap<String,String> res = new HashMap<>();
        res.put("Status" , "Success");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateTraveler(@PathVariable("user-id") Long userId , @RequestBody Map<String , Object> userUpdateFields) throws ResourceNotFoundException {
        travelerService.updateTraveler(userUpdateFields , userId);
        userUpdateFields.put("status" , "Field Updated Successfully");
        return new ResponseEntity<>(userUpdateFields , HttpStatus.OK);
    }
}
