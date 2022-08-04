package com.compass.pb.exams.orders.utils;

import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.domain.request.OfferRequest;
import com.compass.pb.exams.orders.domain.request.OrderRequest;
import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.entities.OfferEntity;
import com.compass.pb.exams.orders.entities.OrderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class MappersUtils {

    public OrderEntity orderRequestToEntity(OrderRequest request) {
        OrderEntity entity = new OrderEntity();
        entity.setAmount(request.getAmount());
        entity.setCpf(request.getCpf());
        entity.setItems(!request.getItems().isEmpty() ?
                request.getItems().stream().map(this::itemRequestToEntity).collect(Collectors.toList())
                : null);
        return entity;
    }

    public ItemEntity itemRequestToEntity(ItemRequest request) {
        if (request == null) return null;
        ItemEntity entity = new ItemEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setSaleValue(request.getValue());
        entity.setExpirationDate(request.getExpirationDate());
        entity.setCreationDate(LocalDateTime.now());
        entity.setOffer(offerRequestToEntity(request.getOffer()));

        return entity;
    }

    public OfferEntity offerRequestToEntity(OfferRequest request) {
        if (request == null) return null;
        OfferEntity entity = new OfferEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setDiscountValue(request.getValue());
        entity.setExpirationDate(request.getExpirationDate());
        entity.setCreationDate(LocalDateTime.now());

        return entity;
    }
}
