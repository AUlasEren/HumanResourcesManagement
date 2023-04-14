package com.hrm.dto.request;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewRegisterRequestDto {
    @Email
    private String email;
    @NotBlank(message = "not null")
    @Size(min = 5,max = 24,message = "password must be at least 5 characters and at most 24 characters")
    private String password;
    private String rePassword;

}
