package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.mailBindingKey}")
    private String mailBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendNewMail(MailModel model){
        rabbitTemplate.convertAndSend(exchange,mailBindingKey,model);
    }
}
