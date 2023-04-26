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
public class UpdateCompanyManagerRequestDto {
    private String id;
    private String image;
    private String email;
    private String address;
    private String phoneNumber;
}
