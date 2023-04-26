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
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.mailBindingKey}")
    private String mailBindingKey;
    @Value("${rabbitmq.queueAdminRegister}")
    private String registerAdminQueue;
    @Value ("${rabbitmq.mailQueue}")
    private String mailQueue;
    @Value("${rabbitmq.queueCompanyManagerRegister}")
    private String registerCompanyManagerQueue;

    @Bean
    Queue registerCompanyManagerQueue(){
        return new Queue(registerCompanyManagerQueue);
    }
    @Bean
    DirectExchange exchangeAuth(){
        return  new DirectExchange(exchange);
    }
    @Bean
    Queue mailQueue(){
        return new Queue(mailQueue);
    }
    @Bean
    Queue registerAdminQueue(){
        return new Queue(registerAdminQueue);
    }
    @Bean
    public Binding bindingRegisterMail(final Queue mailQueue, final DirectExchange exchange){
        return BindingBuilder.bind(mailQueue).to(exchange).with(mailBindingKey);
    }
}
