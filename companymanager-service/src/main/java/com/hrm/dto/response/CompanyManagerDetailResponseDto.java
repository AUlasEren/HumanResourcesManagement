package com.hrm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyManagerDetailResponseDto {
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long companyId;


}
