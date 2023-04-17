package com.hrm.rabbitmq.model;

import com.hrm.repository.enums.ERole;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterModel implements Serializable {

    private Long authId;
    private String email;
    private String password;
    private ERole role;

}
