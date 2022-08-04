package com.compass.pb.exams.orders.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class ItemRequest {

    @NotBlank
    @JsonProperty("nome")
    private String name;

    @NotNull
    @Positive
    @JsonProperty("valor")
    private Double value;

    @NotBlank
    @JsonProperty("descricao")
    private String description;

    @JsonProperty("oferta")
    private OfferRequest offer;

    @Future
    @JsonProperty("data_de_validade")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime expirationDate;
}
