package com.cloudheaven.booking.service;

import com.cloudheaven.booking.model.payment.PaymentStatus;
import com.cloudheaven.booking.repo.PaymentRepo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepo paymentRepo;

    public PaymentService(PaymentRepo paymentRepo){
        this.paymentRepo = paymentRepo;
    }

    public void updatePaymentStatus(PaymentStatus status , String paymentId){
        paymentRepo.updateStatusByPaymentId(status , paymentId);
    }

    public void updateByToken(PaymentStatus status , String token){
        paymentRepo.updateStatusByToken(status , token);
    }

}
