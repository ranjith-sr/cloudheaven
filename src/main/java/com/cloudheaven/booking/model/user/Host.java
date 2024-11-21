package com.cloudheaven.booking.model.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("host")
public class Host extends User{
}
