package com.compass.pb.exams.orders.builders;

import com.compass.pb.exams.orders.entities.OfferEntity;

import java.time.LocalDateTime;

public class OfferEntityBuilder {

    private OfferEntity offerEntity;

    private OfferEntityBuilder() {
    }

    public static OfferEntityBuilder one() {
        OfferEntityBuilder builder = new OfferEntityBuilder();
        builder.offerEntity = new OfferEntity();
        builder.offerEntity.setDescription("test");
        builder.offerEntity.setCreationDate(LocalDateTime.now());
        builder.offerEntity.setDiscountValue(10.0);
        builder.offerEntity.setId(1L);
        builder.offerEntity.setName("OFFERTA10%");
        builder.offerEntity.setExpirationDate(LocalDateTime.now().plusDays(10));

        return builder;
    }

    public OfferEntityBuilder withValidationDate(LocalDateTime date) {
        offerEntity.setExpirationDate(date);
        return this;
    }

    public OfferEntityBuilder withValue(Double value) {
        offerEntity.setDiscountValue(value);
        return this;
    }

    public OfferEntity now() {
        return offerEntity;
    }
}
