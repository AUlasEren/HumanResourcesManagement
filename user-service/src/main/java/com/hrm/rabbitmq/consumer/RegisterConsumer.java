package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterConsumer {

    private final UserService userService;

    @RabbitListener(queues = ("${rabbitmq.queueRegister}"))
    public void newUserCreate(RegisterModel model){
        log.info("User {}", model.toString());
        userService.createUserWithRabbitMq(model);
    }




}
