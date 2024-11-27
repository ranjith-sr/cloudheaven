package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.dto.PropertyBookingRequestDTO;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.model.payment.PropertyPayment;
import com.cloudheaven.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/travelers/{user-id}/booking")
public class BookingController {

    final private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<PropertyPayment> getPaymentDetails(
            @PathVariable("user-id") Long userId
    ) throws ResourceNotFoundException {
        return bookingService.getPaymentDetails(userId);
    }
    @GetMapping("/{payment-id}")
    public PropertyPayment getPaymentDetailsByPaymentId(
            @PathVariable("user-id") Long userId ,
            @PathVariable("payment-id") String paymentId
    ) throws ResourceNotFoundException {
        return bookingService.getPaymentByPaymentId(paymentId , userId);
    }

    @PostMapping
    public ResponseEntity<?> initiateBooking(
            @PathVariable("user-id") Long userId ,
            @RequestBody PropertyBookingRequestDTO propertyBookingRequestDTO
    ) throws Exception {
        Map<String,String> response = bookingService.initiateBooking(userId , propertyBookingRequestDTO);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
