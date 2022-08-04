package com.compass.pb.exams.orders.builders;

import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.entities.OfferEntity;

import java.time.LocalDateTime;

public class ItemEntityBuilder {

    private ItemEntity itemEntity;

    private ItemEntityBuilder() {
    }

    public static ItemEntityBuilder one() {
        ItemEntityBuilder builder = new ItemEntityBuilder();
        builder.itemEntity = new ItemEntity();
        builder.itemEntity.setId(1L);
        builder.itemEntity.setName("Test Item");
        builder.itemEntity.setDescription("item for test execution");
        builder.itemEntity.setSaleValue(99.9);
        builder.itemEntity.setExpirationDate(LocalDateTime.now().plusDays(15));
        builder.itemEntity.setCreationDate(LocalDateTime.now());
        builder.itemEntity.setOffer(OfferEntityBuilder.one().now());

        return builder;
    }

    public ItemEntityBuilder withName(String name) {
        itemEntity.setName(name);
        return this;
    }

    public ItemEntityBuilder withValue(Double value) {
        itemEntity.setSaleValue(value);
        return this;
    }

    public ItemEntity now() {
        return itemEntity;
    }
}
