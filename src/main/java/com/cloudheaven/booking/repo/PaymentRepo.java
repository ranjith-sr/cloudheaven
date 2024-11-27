package com.cloudheaven.booking.repo;

import com.cloudheaven.booking.model.payment.PaymentStatus;
import com.cloudheaven.booking.model.payment.PropertyPayment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<PropertyPayment,String> {

    @Modifying
    @Transactional
    @Query("UPDATE PropertyPayment p SET p.status = :status WHERE p.paymentId = :paymentId")
    public void updateStatusByPaymentId(PaymentStatus status, String paymentId);

    @Modifying
    @Transactional
    @Query("UPDATE PropertyPayment p SET p.status = :status WHERE p.token = :token")
    public void updateStatusByToken(PaymentStatus status, String token);

    Optional<PropertyPayment> findByPaymentIdAndTravelerUserId(String paymentId , Long userId);

    List<PropertyPayment> findByTravelerUserId(Long UserId);
}
