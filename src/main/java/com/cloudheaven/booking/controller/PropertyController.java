package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.PropertyRegisterDTO;
import com.cloudheaven.booking.dto.PropertyResponseDTO;
import com.cloudheaven.booking.dto.PropertyUpdateRequest;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hosts/{user-id}/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<PropertyResponseDTO> getUserProperties(@PathVariable("user-id") Long userId){
        return propertyService.getUserProperties(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponseDTO createProperty(
            @PathVariable("user-id") Long userId ,
            @RequestBody PropertyRegisterDTO propertyRegisterDTO
    ) {
        return propertyService.createProperties(userId , propertyRegisterDTO);
    }

    @GetMapping("/{property-id}")
    public PropertyResponseDTO getPropertyById(
            @PathVariable("user-id") Long userId,
            @PathVariable("property-id") Long propertyId
    ) throws ResourceNotFoundException { 
        return propertyService.getPropertyById(userId , propertyId);
    }

    @DeleteMapping("/{property-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProperty(
            @PathVariable("user-id") Long userId,
            @PathVariable("property-id") Long propertyId
    ) throws ResourceNotFoundException {
        propertyService.deleteProperty(propertyId , userId);
    }

    @PatchMapping("/{property-id}")
    public PropertyResponseDTO updateProperty(
            @PathVariable("user-id") Long userId,
            @PathVariable("property-id") Long propertyId,
            @RequestBody PropertyUpdateRequest propertyUpdateRequest
    ) throws ResourceNotFoundException, IllegalAccessException {
        return propertyService.updateProperty(userId , propertyId , propertyUpdateRequest);
    }
}
