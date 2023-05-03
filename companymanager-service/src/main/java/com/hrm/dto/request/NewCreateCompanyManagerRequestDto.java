package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateCompanyManagerRequestDto {
    private String job;
    private Long companyId;
    private String image;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String name;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String secondName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String lastName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String secondLastName;
    private LocalDate birthDate;
    private String placeOfBirth;
    private Long identificationNumber;
    @Email
    private String email;
    @Size(min=10,max=250,message = "Text size exceeded")
    private String address;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
    private LocalDateTime startDayOfWork;
}
