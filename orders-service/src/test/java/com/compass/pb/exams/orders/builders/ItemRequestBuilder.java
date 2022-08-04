package com.compass.pb.exams.orders.builders;

import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.domain.request.OfferRequest;

import java.time.LocalDateTime;

public class ItemRequestBuilder {

    private ItemRequest itemRequest;

    private ItemRequestBuilder(){}

    public static ItemRequestBuilder one(){
        ItemRequestBuilder builder = new ItemRequestBuilder();
        builder.itemRequest = new ItemRequest();
        builder.itemRequest.setDescription("Item to Test");
        builder.itemRequest.setName("Item 01");
        builder.itemRequest.setValue(44.9);
        builder.itemRequest.setExpirationDate(LocalDateTime.now().plusDays(10));
        builder.itemRequest.setOffer(new OfferRequest());

        return builder;
    }


    public ItemRequestBuilder withValue(Double value){
        itemRequest.setValue(value);
        return this;
    }

    public ItemRequestBuilder withName(String name){
        itemRequest.setName(name);
        return this;
    }

    public ItemRequestBuilder withExpiration(LocalDateTime date){
        itemRequest.setExpirationDate(date);
        return this;
    }


    public ItemRequest now(){
        return itemRequest;
    }

}
