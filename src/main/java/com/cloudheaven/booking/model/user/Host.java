package com.cloudheaven.booking.model.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("host")
@Table(name = "hosts")
@SuperBuilder
public class Host extends User{

    //For Sample Data
    String HostData;

}
