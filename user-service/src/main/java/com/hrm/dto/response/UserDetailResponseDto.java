package com.hrm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto {
  private String image;
  private String name;
  private String lastName;
  private String phoneNumber;
  private String email;

}
