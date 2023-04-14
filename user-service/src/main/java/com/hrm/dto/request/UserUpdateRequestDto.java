package com.hrm.dto.request;

import lombok.*;


import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    private String image;
    private String address;
    private String phoneNumber;

}
