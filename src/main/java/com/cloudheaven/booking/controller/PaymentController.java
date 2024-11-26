package com.cloudheaven.booking.controller;

import com.cloudheaven.booking.model.payment.PaymentStatus;
import com.cloudheaven.booking.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/payment-success")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> paymentSuccess(
            @RequestParam("paymentId") String paymentId ,
            @RequestParam("token") String token ,
            @RequestParam("PayerID") String payerId
    ){
        paymentService.updatePaymentStatus(PaymentStatus.COMPLETED , paymentId);
        Map<String,String> response = new HashMap<>();
        response.put("status" , "Success");
        response.put("data" , "Payment success");
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/payment-failed")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> paymentFailed(
            @RequestParam("token") String token
    ){
        paymentService.updateByToken(PaymentStatus.FAILED , token);
        Map<String,String> response = new HashMap<>();
        response.put("status" , "Failed");
        response.put("data" , "Payment Failed");
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
