package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.RegisterCompanyManagerModel;
import com.hrm.rabbitmq.model.RegisterEmployeeModel;
import com.hrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterEmployeeConsumer {
    private final AuthService authService;
    @RabbitListener(queues = "${rabbitmq.queueEmployeeRegister}")
    public void sendEmployeeMail(RegisterEmployeeModel model){
        log.info("Auth {}",model.toString());
        authService.createEmployeeWithRabbitMq(model);
    }
}
