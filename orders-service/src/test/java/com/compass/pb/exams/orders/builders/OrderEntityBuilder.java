package com.compass.pb.exams.orders.builders;


import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.entities.OrderEntity;

import java.util.Arrays;

public class OrderEntityBuilder {

    private OrderEntity order;

    private OrderEntityBuilder() {
    }

    public static OrderEntityBuilder one() {
        OrderEntityBuilder builder = new OrderEntityBuilder();
        builder.order = new OrderEntity();
        builder.order.setId(1L);
        builder.order.setCpf("99999999999");
        builder.order.setAmount(99.9);
        builder.order.setItems(Arrays.asList(ItemEntityBuilder.one().now()));

        return builder;
    }

    public OrderEntityBuilder withCpf(String cpf){
        order.setCpf(cpf);
        return this;
    }

    public OrderEntityBuilder withItems(ItemEntity... items) {
        order.setItems(Arrays.asList(items));
        return this;
    }

    public OrderEntityBuilder withAmount(Double amount) {
        order.setAmount(amount);
        return this;
    }

    public OrderEntity now() {
        return order;
    }
}
