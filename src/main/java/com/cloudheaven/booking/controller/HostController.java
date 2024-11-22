package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.HostDTO;
import com.cloudheaven.booking.dto.UserRegistrationDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.service.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService){
        this.hostService = hostService;
    }

    @GetMapping
    public List<HostDTO> getAllHosts(){
        return hostService.getAllHosts();
    }

    @PostMapping
    public HostDTO createHost(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return hostService.createHost(userRegistrationDTO);
    }

    @GetMapping("/{user-id}")
    public HostDTO getUserById(@PathVariable("user-id") Long id) throws ResourceNotFoundException {
        return hostService.getUserById(id);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long id) throws ResourceNotFoundException {
        hostService.deleteUser(id);
        HashMap<String,String> res = new HashMap<>();
        res.put("Status" , "Success");
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateUser(@PathVariable("user-id") Long userId , @RequestBody Map<String , Object> userUpdateFields) throws ResourceNotFoundException {
        hostService.updateUser(userUpdateFields , userId);
        userUpdateFields.put("status" , "Field Updated Successfully");
        return new ResponseEntity<>(userUpdateFields , HttpStatus.OK);
    }

}
