package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateEmployeeRequestDto {
    private String image;
    private Long companyId;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String name;
    private String secondName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    private String lastName;

    private String secondLastName;
    private LocalDate birthDate;
    private String placeOfBirth;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[0,2,4,6,8]{1}$", message = "Identifiacation number not valid.")
    private String identificationNumber;
    @Email
    private String email;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String address;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
    private Long salary;
}
