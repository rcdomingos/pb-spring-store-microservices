package com.compass.pb.exams.orders.repositories;

import com.compass.pb.exams.orders.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
