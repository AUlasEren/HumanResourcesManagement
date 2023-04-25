package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.rabbitmq.model.UserMailModel;
import com.hrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserConsumer {
    private final AuthService authService;

    @RabbitListener(queues = "${rabbitmq.queueRegisterMailUser}")
    public void newEmployeeCreate(UserMailModel model){
        log.info("Auth {}", model.toString());
        authService.createAuthWithRabbitMq(model);
    }


    
}
