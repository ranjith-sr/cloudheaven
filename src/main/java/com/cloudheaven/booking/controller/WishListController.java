package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.PropertyResponseDTO;
import com.cloudheaven.booking.dto.TravelerDTO;
import com.cloudheaven.booking.dto.WishListRequest;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/travelers/{user-id}/wishlists")
public class WishListController {

    final private WishListService wishListService;

    public WishListController(WishListService wishListService){
        this.wishListService = wishListService;
    }

    @GetMapping
    public List<PropertyResponseDTO> getWishLists(
            @PathVariable("user-id") Long userId
    ){
        return wishListService.getWishList(userId);
    }

    @PostMapping
    public ResponseEntity<?> addToWishList(
            @PathVariable("user-id") Long userId,
            @RequestBody WishListRequest wishListRequest
    ) throws ResourceNotFoundException {
        TravelerDTO travelerDTO = wishListService.addWishListToUser(userId , wishListRequest);
        Map<String , Object> responseBody = new HashMap<>();
        responseBody.put("Status" , "Successfully Added");
        responseBody.put("data" , travelerDTO);
        return new ResponseEntity<>(responseBody , HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletePropertyFromWishList(
            @PathVariable("user-id") Long userId,
            @RequestBody WishListRequest wishListRequest
    ) throws ResourceNotFoundException {
        wishListService.deletePropertyInWishList(userId , wishListRequest);
    }

}
