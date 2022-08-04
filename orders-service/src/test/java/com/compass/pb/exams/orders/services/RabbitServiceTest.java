package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.domain.request.RabbitPaymentRequest;
import com.compass.pb.exams.orders.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = RabbitService.class)
class RabbitServiceTest {

    @Autowired
    private RabbitService service;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Value("${custom.rabbit-payment-queue}")
    private String paymentQueue;

    @Test
    void shouldSendToQueue_whenReceiveAMessage() {
        service.sendPaymentToQueue(new OrderDto());

        verify(rabbitTemplate, times(1)).convertAndSend(eq(paymentQueue), any(RabbitPaymentRequest.class));
    }

    @Test
    void shouldDoNothing_whenThrowException() {
        doThrow(RuntimeException.class).when(rabbitTemplate).convertAndSend(eq(paymentQueue), any(RabbitPaymentRequest.class));

        service.sendPaymentToQueue(new OrderDto());

        verify(rabbitTemplate, times(1)).convertAndSend(eq(paymentQueue), any(RabbitPaymentRequest.class));
    }

}