package com.cloudheaven.booking.model.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("traveler")
@Table(name = "travelers")
@SuperBuilder
public class Traveler extends User{

    //For Traveler Sample Data
    String TravelerData;

}
