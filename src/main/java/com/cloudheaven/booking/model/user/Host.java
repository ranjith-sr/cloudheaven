package com.cloudheaven.booking.model.user;

import com.cloudheaven.booking.model.property.Property;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("host")
@Table(name = "hosts")
@SuperBuilder
public class Host extends User{

    @OneToMany(mappedBy = "host")
    @JsonManagedReference
    List<Property> properties;

}
