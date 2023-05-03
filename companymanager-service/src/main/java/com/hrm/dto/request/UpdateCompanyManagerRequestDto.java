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
public class UpdateCompanyManagerRequestDto {
    private String id;
    private String image;
    @Email
    private String email;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String address;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
}
