package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateEmployeeRequestDto {
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
}
