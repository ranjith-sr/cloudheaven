package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.PropertyRegisterDTO;
import com.cloudheaven.booking.dto.PropertyResponseDTO;
import com.cloudheaven.booking.dto.PropertyUpdateRequest;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.PropertyResponseDTOMapper;
import com.cloudheaven.booking.model.property.Property;
import com.cloudheaven.booking.model.user.Host;
import com.cloudheaven.booking.repo.PropertyRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepo propertyRepo;
    private final PropertyResponseDTOMapper propertyResponseDTOMapper;
    public PropertyService(PropertyRepo propertyRepo , PropertyResponseDTOMapper propertyResponseDTOMapper){
        this.propertyRepo = propertyRepo;
        this.propertyResponseDTOMapper = propertyResponseDTOMapper;
    }

    public List<PropertyResponseDTO> getUserProperties(Long userId){
        return propertyRepo.findByHostUserId(userId)
                        .stream()
                        .map(propertyResponseDTOMapper)
                        .collect(Collectors.toList());
    }

    public PropertyResponseDTO createProperties(Long userId , PropertyRegisterDTO propertyRegisterDTO){
        Property property = Property.builder()
                .propertyName(propertyRegisterDTO.getPropertyName())
                .description(propertyRegisterDTO.getDescription())
                .propertyType(propertyRegisterDTO.getPropertyType())
                .payPerDay(propertyRegisterDTO.getPayPerDay())
                .payPerNight(propertyRegisterDTO.getPayPerNight())
                .payPerFullDay(propertyRegisterDTO.getPayPerFullDay())
                .address(propertyRegisterDTO.getAddress())
                .host(Host.builder()
                        .userId(userId)
                        .build()
                )
                .build();
        return propertyResponseDTOMapper.apply(propertyRepo.save(property));
    }

    public PropertyResponseDTO getPropertyById(Long userId , Long propertyId) throws ResourceNotFoundException{
        Property property = propertyRepo.findByPropertyIdAndHostUserId(propertyId , userId)
                .orElseThrow(() -> new ResourceNotFoundException("Property Not Found"));
        return propertyResponseDTOMapper.apply(property);
    }

    public void deleteProperty(Long propertyId , Long userId){
        propertyRepo.deleteByPropertyIdAndHostUserId(propertyId , userId);
    }

    public PropertyResponseDTO updateProperty(Long userId , Long propertyId , PropertyUpdateRequest propertyUpdateRequest) throws ResourceNotFoundException, IllegalAccessException {
        Property property = propertyRepo.findByPropertyIdAndHostUserId(propertyId , userId)
                .orElseThrow(()-> new ResourceNotFoundException("Property Not Found"));
        if(propertyUpdateRequest.propertyName() != null) property.setPropertyName(propertyUpdateRequest.propertyName());
        if(propertyUpdateRequest.description() != null) property.setDescription(propertyUpdateRequest.description());
        if(propertyUpdateRequest.propertyType() != null) property.setPropertyType(propertyUpdateRequest.propertyType());
        if(propertyUpdateRequest.payPerDay() != null) property.setPayPerDay(propertyUpdateRequest.payPerDay());
        if(propertyUpdateRequest.payPerNight() != null) property.setPayPerNight(propertyUpdateRequest.payPerNight());
        if(propertyUpdateRequest.payPerFullDay() != null) property.setPayPerFullDay(propertyUpdateRequest.payPerFullDay());
        if(propertyUpdateRequest.apartmentNumber() != null) property.getAddress().setApartmentNumber(propertyUpdateRequest.apartmentNumber());
        if(propertyUpdateRequest.street() != null) property.getAddress().setStreet(propertyUpdateRequest.street());
        if(propertyUpdateRequest.city() != null) property.getAddress().setCity(propertyUpdateRequest.city());
        if(propertyUpdateRequest.state() != null) property.getAddress().setState(propertyUpdateRequest.state());
        if(propertyUpdateRequest.postalCode() != null) property.getAddress().setPostalCode(propertyUpdateRequest.postalCode());
        return propertyResponseDTOMapper.apply(propertyRepo.save(property));
    }

}