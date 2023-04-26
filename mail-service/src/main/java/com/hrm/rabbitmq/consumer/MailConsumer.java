package com.hrm.rabbitmq.consumer;

import com.hrm.rabbitmq.model.MailModel;
import com.hrm.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailConsumer {
    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "${rabbitmq.mailQueue}")
    public void sendMail(MailModel model){
        log.info("Model {}", model.toString());
        mailSenderService.sendMail(model);
    }

}
