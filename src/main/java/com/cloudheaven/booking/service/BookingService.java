package com.cloudheaven.booking.service;

import com.cloudheaven.booking.dto.PropertyBookingRequestDTO;
import com.cloudheaven.booking.exceptions.CustomError;
import com.cloudheaven.booking.exceptions.ResourceNotFoundException;
import com.cloudheaven.booking.model.payment.PaymentStatus;
import com.cloudheaven.booking.model.payment.PropertyPayment;
import com.cloudheaven.booking.model.property.Property;
import com.cloudheaven.booking.model.user.Traveler;
import com.cloudheaven.booking.repo.PaymentRepo;
import com.cloudheaven.booking.repo.PropertyRepo;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class BookingService {

    final private PropertyRepo propertyRepo;
    final private PaymentRepo paymentRepo;
    final private APIContext apiContext;

    public BookingService(
            PropertyRepo propertyRepo,
            APIContext apiContext,
            PaymentRepo paymentRepo
    ){
        this.propertyRepo = propertyRepo;
        this.apiContext = apiContext;
        this.paymentRepo = paymentRepo;
    }

    public PropertyPayment getPaymentByPaymentId(String getPaymentId , Long userId) throws ResourceNotFoundException {
        PropertyPayment propertyPayment = paymentRepo.findByPaymentIdAndTravelerUserId(getPaymentId , userId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment Not Found"));
        return propertyPayment;
    }

    public List<PropertyPayment> getPaymentDetails(Long userId) {
        return paymentRepo.findByTravelerUserId(userId);
    }

    public Map<String, String> initiateBooking(Long userId , PropertyBookingRequestDTO propertyBookingRequestDTO) throws Exception {
        Map<String,String> response = new HashMap<>();
        Property property = propertyRepo.findById(propertyBookingRequestDTO.getPropertyId())
                .orElseThrow( ()->new ResourceNotFoundException("Property Not Found"));

        Payment paymentResult = paymentInitiate(property.getPayPerDay() * propertyBookingRequestDTO.getNumOfGuest());

        if(Objects.equals(paymentResult.getState(), "failed"))
            throw new CustomError("Failed to Initiate Payment");

        double payAmount = Double.parseDouble(paymentResult.getTransactions().getFirst().getAmount().getTotal());

        String approvalLink = paymentResult.getLinks().stream()
                .filter(link -> link.getRel().equals("approval_url"))
                .findFirst()
                .map(link -> link.getHref())
                .orElse(null);

        String token = getToken(approvalLink);

        PropertyPayment propertyPayment = PropertyPayment.builder()
                .paymentId(paymentResult.getId())
                .amount(payAmount)
                .currency(paymentResult.getTransactions().getFirst().getAmount().getCurrency())
                .status(PaymentStatus.PENDING)
                .paymentMethod("paypal")
                .createdAt(ZonedDateTime.now())
                .paymentIntent("sale")
                .token(token)
                .checkIn(propertyBookingRequestDTO.getCheckIn())
                .checkOut(propertyBookingRequestDTO.getCheckOut())
                .traveler(Traveler.builder().userId(userId).build())
                .property(property)
                .build();
        paymentRepo.save(propertyPayment);
        response.put("approval_link" , approvalLink);
        response.put("paymentId" , paymentResult.getId());
        response.put("userId" , userId.toString());
        response.put("amount" , String.valueOf(payAmount));
        response.put("checkIn" , propertyBookingRequestDTO.getCheckIn().toString());
        response.put("checkOut" , propertyBookingRequestDTO.getCheckOut().toString());
        response.put("paymentMethod" , "paypal");
        return response;
    }

    Payment paymentInitiate(Double rent) throws PayPalRESTException {

        Transaction transaction = new Transaction();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(rent.toString());
        transaction.setAmount(amount);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://localhost:8080/api/v1/payment-success");
        redirectUrls.setCancelUrl("http://localhost:8080/api/v1/payment-failed");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent("sale");
        payment.setTransactions(Collections.singletonList(transaction));
        payment.setRedirectUrls(redirectUrls);
        return payment.create(apiContext);
    }

    String getToken(String urlString) {
        String token = "";
        try {
            URL url = new URL(urlString);
            String query = url.getQuery();
            Map<String, String> queryParams = new HashMap<>();
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                queryParams.put(pair[0], pair[1]);
            }
            token = queryParams.get("token");
        } catch (Exception exception){
            System.out.println(exception.fillInStackTrace());
        }
        return token;
    }
}
