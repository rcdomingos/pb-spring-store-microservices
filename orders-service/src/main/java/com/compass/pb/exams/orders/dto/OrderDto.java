package com.compass.pb.exams.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("itens")
    private List<ItemDto> items;

    @JsonProperty("total")
    private Double amount;
}
