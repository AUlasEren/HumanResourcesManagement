package com.hrm.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailResponseDto {
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long companyId;
}
