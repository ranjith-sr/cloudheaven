package com.cloudheaven.booking.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_role")
@Table(name = "users")
@SuperBuilder
public class User {

    @Id
    @GeneratedValue
    Long userId;

    @Column(unique = true)
    String email;

    @NotEmpty
    private String password;

    String name;

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
