package shop.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${jsa.rabbitmq.queue.creation_logs}")
    String queueCreatedName;
    @Bean
    Queue createdQueue() {
        return new Queue(queueCreatedName, false);
    }

    @Value("${jsa.rabbitmq.queue.update_logs}")
    String queueUpdatedName;
    @Bean
    Queue updatedQueue() {
        return new Queue(queueUpdatedName, false);
    }

    @Value("${jsa.rabbitmq.queue.deletion_logs}")
    String queueDeletedName;
    @Bean
    Queue deletedQueue() {
        return new Queue(queueDeletedName, false);
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
