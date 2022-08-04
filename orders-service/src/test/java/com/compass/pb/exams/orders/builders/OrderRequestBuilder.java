package com.compass.pb.exams.orders.builders;

import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.domain.request.OrderRequest;

import java.util.Arrays;

public class OrderRequestBuilder {

    private OrderRequest orderRequest;

    private OrderRequestBuilder() {
    }

    public static OrderRequestBuilder one() {
        OrderRequestBuilder builder = new OrderRequestBuilder();
        builder.orderRequest = new OrderRequest();
        builder.orderRequest.setCpf("99999999999");
        builder.orderRequest.setAmount(999.99);
        builder.orderRequest.setItems(Arrays.asList(ItemRequestBuilder.one().now()));

        return builder;
    }

    public OrderRequestBuilder withAmount(Double amount) {
        orderRequest.setAmount(amount);
        return this;
    }

    public OrderRequestBuilder withCpf(String cpf) {
        orderRequest.setCpf(cpf);
        return this;
    }

    public OrderRequestBuilder withItems(ItemRequest... items) {
        orderRequest.setItems(Arrays.asList(items));
        return this;
    }

    public OrderRequest now() {
        return orderRequest;
    }

}
