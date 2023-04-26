package com.hrm.rabbitmq.producer;

import com.hrm.rabbitmq.model.RegisterEmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterEmployeeProducer {
    @Value("${rabbitmq.exchange-employee}")
    private String exchangeEmployee;
    @Value("${rabbitmq.registerEmployeeBindingKey}")
    private String registerEmployeeBindingKey;
    private final RabbitTemplate rabbitTemplate;

    public void sendNewEmployee(RegisterEmployeeModel model){
        rabbitTemplate.convertAndSend(exchangeEmployee,registerEmployeeBindingKey,model);
    }
}
