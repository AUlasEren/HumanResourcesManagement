package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateAdminRequestDto {
    private String image;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    @NotBlank(message = "Name and Surname must not be null")
    private String name;
    private String secondName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name and Surname must be letters only.")
    @NotBlank(message = "Name and Surname must not be null")
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
}
