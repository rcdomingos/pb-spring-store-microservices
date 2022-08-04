package com.compass.pb.exams.payments.repositories;

import com.compass.pb.exams.payments.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
