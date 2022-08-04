package com.compass.pb.exams.orders.controllers;

import com.compass.pb.exams.orders.domain.request.OrderRequest;
import com.compass.pb.exams.orders.dto.OrderDto;
import com.compass.pb.exams.orders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedido")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> insertNewOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrderns(@RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) String cpf) {
        return (cpf == null || cpf.isBlank())
                ? ResponseEntity.ok(service.getAllOrderns(sort))
                : ResponseEntity.ok(service.getAllOrdernsByCpf(sort, cpf));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderInformation(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(service.updateOrderInformation(id, orderRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

}
