package com.compass.pb.exams.orders.controllers;

import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.dto.ItemDto;
import com.compass.pb.exams.orders.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService service;

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> updateItems(@PathVariable Long id, @RequestBody ItemRequest request) {
        return ResponseEntity.ok(service.updateItemById(id, request));
    }

}
