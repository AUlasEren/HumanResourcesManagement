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
}
