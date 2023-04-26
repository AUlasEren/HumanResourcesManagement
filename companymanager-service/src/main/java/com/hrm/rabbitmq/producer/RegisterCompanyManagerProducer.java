package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.RegisterCompanyManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCompanyManagerProducer {
    @Value("${rabbitmq.exchangecompanymanager}")
    private String exchangeCompanyManager;

    @Value("${rabbitmq.registerCompanyManagerBindingKey}")
    private String registerCompanyManagerBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendNewCompanyManager(RegisterCompanyManagerModel model){
        rabbitTemplate.convertAndSend(exchangeCompanyManager,registerCompanyManagerBindingKey,model);
    }
}
