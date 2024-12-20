package com.cloudheaven.booking;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {

    @Value("${paypal.client.id}")
    String clientId;

    @Value("${paypal.client.secret}")
    String clientPassword;

    @Value("${paypal.mode}")
    String mode;

    @Bean
    public APIContext apiContext(){
        return new APIContext(clientId , clientPassword , mode);
    }

}
