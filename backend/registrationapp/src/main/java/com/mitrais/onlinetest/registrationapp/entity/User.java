package com.mitrais.onlinetest.registrationapp.entity;
/*
 * Dear Maintainer,
 *
 * When I wrote this code, only I and God knew what it was. Now, only God knows!
 *
 * So, If you're done, trying to 'optimize' this routine (and failed).
 * Please, increment the following counter as a warning to the next guy:
 * total_hours_wasted_here: 999;
 *
 * Sincerely Yours, Hooman
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mitrais.onlinetest.registrationapp.service.UserService;
import com.mitrais.onlinetest.registrationapp.service.UserValidationService;
import com.mitrais.onlinetest.registrationapp.validation.UniqueEmail;
import com.mitrais.onlinetest.registrationapp.validation.UniqueMobileNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "^(^62\\s?)(\\d{3,4}-?){2}\\d{3,4}$", message = "Invalid Indonesian Mobile Number Format\n(Start with '62' and followed by 9-12 digits)")
    @UniqueMobileNumber(service = UserValidationService.class, fieldName = "mobileNumber", message= "Mobile Number is already used")
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @NotEmpty(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 1)
    private String gender;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @UniqueEmail(service = UserValidationService.class, fieldName = "email", message= "Email is already used")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
