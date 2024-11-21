package com.cloudheaven.booking.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_role")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private String name;

    private String gender;

    @NotNull
    private Long mobile_no;

    private ZonedDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private UserVerified verified;

    private Date dob;

    @Enumerated(EnumType.STRING)
    private UserType account_type;

}
