package com.cloudheaven.booking.model.user;

import com.cloudheaven.booking.model.property.Property;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("traveler")
@Table(name = "travelers")
@SuperBuilder
public class Traveler extends User{

    @ManyToMany
    Set<Property> wishList;

}
