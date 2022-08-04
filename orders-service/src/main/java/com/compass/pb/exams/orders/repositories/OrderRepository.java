package com.compass.pb.exams.orders.repositories;

import com.compass.pb.exams.orders.entities.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByCpf(Sort sorted, String cpf);
}
