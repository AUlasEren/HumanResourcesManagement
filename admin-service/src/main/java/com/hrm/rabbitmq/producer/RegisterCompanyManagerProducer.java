package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.RegisterAdminModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCompanyManagerProducer {
    @Value("${rabbitmq.exchange-companymanager}")
    private String companyManagerExchange;
    @Value("${rabbitmq.registerCompanyManagerBindingKey}")
    private String registerCompanyManagerKey;
    private final RabbitTemplate rabbitTemplate;

    public void sendNewAdmin(RegisterAdminModel model){
        rabbitTemplate.convertAndSend(companyManagerExchange,registerCompanyManagerKey,model);
    }
}
