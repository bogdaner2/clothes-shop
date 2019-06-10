package com.konorin.shops_api.Producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.AmqpTemplate;

@Configuration
public class RabbitConfig {

    @Value("${jsa.rabbitmq.queue.creation_logs}")
    String creationLogsQueue;
    @Bean
    Queue createdQueue() {
        return new Queue(creationLogsQueue, false);
    }

    @Value("${jsa.rabbitmq.queue.update_logs}")
    String updateLogsQueue;
    @Bean
    Queue updatedQueue() {
        return new Queue(updateLogsQueue, false);
    }

    @Value("${jsa.rabbitmq.queue.deletion_logs}")
    String deletionLogsQueue;
    @Bean
    Queue deletedQueue() {
        return new Queue(deletionLogsQueue, false);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("rabbit-mq", 5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}

