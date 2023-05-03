package com.hrm.dto.response;

import com.hrm.repository.enums.EStatus;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailResponseDto {
    private String companyName;
    private String title;
    private String mersisNumber;
    private String taxNumber;
    private String TaxAdministration;
    private String image;
    private String phoneNumber;
    private String address;
    private String email;
    private int numberOfEmployees;
    private String foundationYear;
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
    private EStatus status;
}
