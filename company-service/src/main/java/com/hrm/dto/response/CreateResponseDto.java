package com.hrm.dto.response;

import com.hrm.repository.enums.EStatus;
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
public class CreateResponseDto {

    private String companyName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Title name must be letters only.")
    private String title;
    private String mersisNumber;
    private String taxNumber;
    private String TaxAdministration;
    private String image;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
    @Size(min=10,max=250,message = "Text size exceeded")
    private String address;
    @Email
    private String email;
    private int numberOfEmployees;
    private String foundationYear;
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
}
