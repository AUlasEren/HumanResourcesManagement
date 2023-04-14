package com.hrm.dto.request;

import lombok.*;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    private String image;
    private String address;
    private String phoneNumber;

}
