package com.hrm.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-employee}")
    private String exchangeEmployee;
    @Value("${rabbitmq.queueEmployeeRegister}")
    private String registerEmployeeQueue;
    @Value("${rabbitmq.registerEmployeeBindingKey}")
    private String registerEmployeeBindingKey;
    @Bean
    DirectExchange exchangeRegisterEmployee(){
        return new DirectExchange(exchangeEmployee);
    }
    @Bean
    Queue registerEmployeeQueue(){
        return new Queue(registerEmployeeQueue);
    }
    @Bean
    public Binding registerEmployeeBindingKey(final Queue registerEmployeeQueue, final DirectExchange exchangeRegisterEmployee){
        return BindingBuilder.bind(registerEmployeeQueue).to(exchangeRegisterEmployee).with(registerEmployeeBindingKey);
    }
}
