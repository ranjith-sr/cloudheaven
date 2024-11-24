package com.cloudheaven.booking.dto;

import jakarta.validation.constraints.NotNull;

public record WishListRequest(

        @NotNull(message = "Property Id must not null")
        Long propertyId
) {
}
