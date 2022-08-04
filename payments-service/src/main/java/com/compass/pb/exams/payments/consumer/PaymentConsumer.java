package com.compass.pb.exams.payments.consumer;

import com.compass.pb.exams.payments.config.RabbitMqConfig;
import com.compass.pb.exams.payments.domain.PaymentRequest;
import com.compass.pb.exams.payments.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService service;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void listenerPaymentQueue(@Payload PaymentRequest payment) {
        log.debug("listenerPaymentQueue() - Receiving message from queue: {}", payment);
        service.addNewPaymenteFromQueue(payment);
    }

}
