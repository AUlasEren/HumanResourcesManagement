package com.hrm.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponseDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String image;
}
