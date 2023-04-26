package com.hrm.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-manager}")
    private String exchangeCompanyManager;
    @Value("${rabbitmq.queueCompanyManagerRegister}")
    private String registerCompanyManagerQueue;
    @Value("${rabbitmq.registerCompanyManagerBindingKey}")
    private String registerCompanyManagerBindingKey;
    @Bean
    DirectExchange exchangeCompanyManager(){
        return new DirectExchange(exchangeCompanyManager);
    }
    @Bean
    Queue registerCompanyManagerQueue(){
        return new Queue(registerCompanyManagerQueue);
    }
    @Bean
    public Binding registerCompanyManagerBindingKey(final Queue registerCompanyManagerQueue, final DirectExchange exchangeRegisterCompanyManager){
        return BindingBuilder.bind(registerCompanyManagerQueue).to(exchangeRegisterCompanyManager).with(registerCompanyManagerBindingKey);
    }
}
