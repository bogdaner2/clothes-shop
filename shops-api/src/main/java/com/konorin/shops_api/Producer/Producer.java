package com.konorin.shops_api.Producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produceMessage(String message, String queueName){
        amqpTemplate.convertAndSend(queueName, message);
        System.out.println("Send message = " + message + "to " + queueName + " queue.");
    }
}