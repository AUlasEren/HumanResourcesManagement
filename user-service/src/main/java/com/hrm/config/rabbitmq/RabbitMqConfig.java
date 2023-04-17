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

    @Value("${rabbitmq.queueRegister}")
    private String queueNameRegister;

    @Value("${rabbitmq.registerkey}")
    private String registerBindingKey;

    @Value("${rabbitmq.exchange-user}")
    private String exchangeUser;


    @Bean
    Queue registerQueue(){
        return new Queue(queueNameRegister);
    }

    @Bean
    DirectExchange exchangeUser(){
        return new DirectExchange(exchangeUser);
    }






}
