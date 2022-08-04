package com.compass.pb.exams.payments.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequest {

    @JsonProperty("order_id")
    private Long orderId;

    private Double amount;
}
