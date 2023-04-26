package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.RegisterAdminModel;
import com.hrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterAdminConsumer {
    private final AuthService authService;
    @RabbitListener(queues = "${rabbitmq.queueAdminRegister}")
    public void sendAdminMail(RegisterAdminModel model){
        log.info("Auth {}",model.toString());
        authService.createAdminWithRabbitMq(model);
    }

}
