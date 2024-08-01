package com.haydikodlayalim.services.concretes;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haydikodlayalim.core.configurations.RabbitMqConfig;
import com.haydikodlayalim.services.abstracts.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishMessage(String email, String message,String city) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("publisherEmail", email);
        messageProperties.setHeader("city", city);

        Message msg = new Message(message.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, "", msg);
    }

}
