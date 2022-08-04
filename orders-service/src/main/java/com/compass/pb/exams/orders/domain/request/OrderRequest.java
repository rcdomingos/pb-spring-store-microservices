package com.compass.pb.exams.orders.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class OrderRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{11}$",message = "deve ter apenas 11 numeros")
    @JsonProperty("cpf")
    private String cpf;


    @Valid
    @NotNull
    @Size(min = 1)
    @JsonProperty("itens")
    private List<ItemRequest> items;

    @NotNull
    @Positive
    @JsonProperty("total")
    private Double amount;
}
