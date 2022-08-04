package com.compass.pb.exams.payments.services;

import com.compass.pb.exams.payments.domain.PaymentRequest;
import com.compass.pb.exams.payments.entities.PaymentEntity;
import com.compass.pb.exams.payments.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public void addNewPaymenteFromQueue(PaymentRequest request) {
        log.info("addNewPaymenteFromQueue() - Start - processing request with orderId: {}", request.getOrderId());
        PaymentEntity saved = repository.save(new PaymentEntity(null, request.getOrderId(), request.getAmount(), LocalDateTime.now()));
        log.info("addNewPaymenteFromQueue() - End - finalized id: {}", saved.getId());
    }
}
