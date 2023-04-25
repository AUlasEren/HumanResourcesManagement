package com.hrm.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queueRegister}")
    private String queueNameRegister;
    @Value("${rabbitmq.queueRegisterMailUser}") //
    private String queueRegisterMailUser;

    @Value("${rabbitmq.registerkey}")
    private String registerBindingKey;

    @Value("${rabbitmq.registerusermailbindingkey}") //
    private String registerusermailbindingkey;

    @Value("${rabbitmq.exchange-user}")
    private String exchangeUser;
    @Value("${rabbitmq.exchangemail-user}") //
    private String exchangeMailUser;


    @Bean
    Queue registerQueue() {
        return new Queue(queueNameRegister);
    }
    @Bean
    @Primary
    Queue queueRegisterMailUser() {
        return new Queue(queueRegisterMailUser);
    } //

    @Bean
    DirectExchange exchangeUser() {
        return new DirectExchange(exchangeUser);
    }

    @Bean
    DirectExchange exchangeMailUser() {
        return new DirectExchange(exchangeMailUser);
    } //

    @Bean
    public Binding bindingUserRegister(final Queue queueRegisterMailUser, final DirectExchange exchangeMailUser){
        return BindingBuilder.bind(queueRegisterMailUser).to(exchangeMailUser).with(registerusermailbindingkey);
    }

}
