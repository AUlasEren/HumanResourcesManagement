package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.RegisterAdminModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCompanyManagerProducer {
    @Value("${rabbitmq.exchange-admin}")
    private String exchangeAdmin;
    @Value("${rabbitmq.registerCompanyManagerBindingKey}")
    private String registerCompanyManagerKey;
    private final RabbitTemplate rabbitTemplate;

    public void sendNewAdmin(RegisterAdminModel model){
        rabbitTemplate.convertAndSend(exchangeAdmin,registerCompanyManagerKey,model);
    }
}
