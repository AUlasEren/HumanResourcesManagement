package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAdminRequestDto {
    private String id;
    private String image;
    private String address;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
}
