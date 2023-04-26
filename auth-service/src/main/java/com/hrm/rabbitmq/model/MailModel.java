package com.hrm.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailModel implements Serializable {
    private String email;
    private String activationCode;
}
