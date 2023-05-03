package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmployeeRequestDto {
    private String id;
    private String image;
    @Size(min=5,max=250,message = "Text size exceeded")
    private String address;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Phone number not valid.")
    private String phoneNumber;
}
