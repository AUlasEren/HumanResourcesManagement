package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.RegisterAdminModel;
import com.hrm.rabbitmq.model.RegisterCompanyManagerModel;
import com.hrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterCompanyManagerConsumer {
    private final AuthService authService;
    @RabbitListener(queues = "${rabbitmq.queueCompanyManagerRegister}")
    public void sendAdminMail(RegisterCompanyManagerModel model){
        log.info("Auth {}",model.toString());
        authService.createEmployeeManagerWithRabbitMq(model);
    }
}
