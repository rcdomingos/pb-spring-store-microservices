package com.compass.pb.exams.orders.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RabbitPaymentRequest {

    @JsonProperty("order_id")
    private Long orderId;

    private Double amount;
}
