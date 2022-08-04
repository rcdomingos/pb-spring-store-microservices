package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.domain.request.RabbitPaymentRequest;
import com.compass.pb.exams.orders.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${custom.rabbit-payment-queue}")
    private String paymentQueue;

    public void sendPaymentToQueue(OrderDto orderDto) {
        RabbitPaymentRequest request = new RabbitPaymentRequest(orderDto.getId(), orderDto.getAmount());
        sendPaymenteMessage(request);
    }

    private void sendPaymenteMessage(RabbitPaymentRequest request) {
        try {
            rabbitTemplate.convertAndSend(paymentQueue, request);
        } catch (Exception e) {
            log.error("sendPaymenteMessage() - Erro to send payment to rabbit: {}", e.getMessage());
        }
    }
}
