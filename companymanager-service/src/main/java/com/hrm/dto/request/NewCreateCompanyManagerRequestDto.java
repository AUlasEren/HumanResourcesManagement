package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateCompanyManagerRequestDto {
    private String job;
    private String image;
    private String name;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private String placeOfBirth;
    private Long identificationNumber;
    private String email;
    private String address;
    private String phoneNumber;
    private LocalDateTime startDayOfWork;
}
