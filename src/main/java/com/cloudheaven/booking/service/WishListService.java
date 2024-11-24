package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.PropertyResponseDTO;
import com.cloudheaven.booking.dto.TravelerDTO;
import com.cloudheaven.booking.dto.WishListRequest;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.mapper.PropertyResponseDTOMapper;
import com.cloudheaven.booking.mapper.TravelerDTOMapper;
import com.cloudheaven.booking.model.property.Property;
import com.cloudheaven.booking.model.user.Traveler;
import com.cloudheaven.booking.repo.PropertyRepo;
import com.cloudheaven.booking.repo.TravelerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListService {
    private final TravelerRepo travelerRepo;
    private final PropertyRepo propertyRepo;
    private final TravelerDTOMapper travelerDTOMapper;
    private final PropertyResponseDTOMapper propertyResponseDTOMapper;

    public WishListService(
            TravelerRepo travelerRepo ,
            PropertyRepo propertyRepo ,
            TravelerDTOMapper travelerDTOMapper,
            PropertyResponseDTOMapper propertyResponseDTOMapper
    ){
            this.travelerRepo = travelerRepo;
            this.propertyRepo = propertyRepo;
            this.travelerDTOMapper = travelerDTOMapper;
            this.propertyResponseDTOMapper = propertyResponseDTOMapper;
    }

    public List<PropertyResponseDTO> getWishList(Long userId){
        return travelerRepo.findWishListByUserId(userId).stream()
                .map(propertyResponseDTOMapper)
                .collect(Collectors.toList());
    }

    public TravelerDTO addWishListToUser(Long userId , WishListRequest wishListRequest) throws ResourceNotFoundException {
        Traveler traveler = travelerRepo.findById(userId)
                .orElseThrow( ()-> new ResourceNotFoundException("User Not Found"));
        Property property = propertyRepo.findById(wishListRequest.propertyId())
                .orElseThrow( ()-> new ResourceNotFoundException("Property Not Found"));
        traveler.getWishList().add(property);
        return travelerDTOMapper.apply(travelerRepo.save(traveler));
    }

    public void deletePropertyInWishList(Long userId , WishListRequest wishListRequest) throws ResourceNotFoundException {
        Traveler traveler = travelerRepo.findById(userId)
                .orElseThrow( ()-> new ResourceNotFoundException("User Not Found"));
        boolean isPresent = traveler.getWishList().stream().anyMatch(
                property -> property.getPropertyId() == wishListRequest.propertyId()
        );
        if(!isPresent)
            throw new ResourceNotFoundException("Property Not Found in WishList");
        traveler.setWishList(
                traveler.getWishList().stream().filter(property -> property.getPropertyId() != wishListRequest.propertyId())
                        .collect(Collectors.toSet())
        );
        travelerRepo.save(traveler);
    }

}
