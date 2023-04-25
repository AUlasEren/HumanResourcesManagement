package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.rabbitmq.model.UserMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    @Value("${rabbitmq.exchangemail-user}")
    private String directExchange;

    @Value("${rabbitmq.registerusermailbindingkey}")
    private String registerUserBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendNewUser(UserMailModel model){
        rabbitTemplate.convertAndSend(directExchange,registerUserBindingKey,model);
    }
}
