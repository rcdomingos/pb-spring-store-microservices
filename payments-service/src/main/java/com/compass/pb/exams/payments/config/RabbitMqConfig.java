package com.compass.pb.exams.payments.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    public static final String EXCHANGE_NAME = "storePayment.Exchange";
    public static final String QUEUE = "storePayment.Queue";
    public static final String ROUTING_KEY = "storePayment.RoutingKey";

    private final ConnectionFactory connectionFactory;

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @PostConstruct
    public void createRabbitElements() {
        log.info("createRabbitElements() - START - Create Rabbit configuration");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        createExchange(rabbitAdmin);
        createPaymentQueue(rabbitAdmin);
        log.info("createRabbitElements() - END - Finished configuration");
    }

    private void createExchange(RabbitAdmin rabbitAdmin) {
        log.debug("createExchange() - INFO - Created exchange {}", EXCHANGE_NAME);
        Exchange exchange = ExchangeBuilder
                .directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();

        rabbitAdmin.declareExchange(exchange);
    }

    private void createPaymentQueue(RabbitAdmin rabbitAdmin) {
        log.debug("createSkuQueue() - INFO - Created queue: {}, routing key: {}", QUEUE, ROUTING_KEY);
        Queue queue = QueueBuilder.durable(QUEUE)
                .build();

        Binding binding = new Binding(
                QUEUE,
                Binding.DestinationType.QUEUE,
                EXCHANGE_NAME,
                ROUTING_KEY,
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }

}
