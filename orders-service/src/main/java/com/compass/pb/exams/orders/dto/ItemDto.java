package com.compass.pb.exams.orders.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("valor")
    private Double value;

    @JsonProperty("descricao")
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("data_de_criacao")
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("data_de_validade")
    private LocalDateTime expirationDate;

    @JsonProperty("oferta")
    private OfferDto offer;
}
