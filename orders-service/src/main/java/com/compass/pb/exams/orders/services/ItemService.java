package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.dto.ItemDto;
import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.repositories.ItemRepository;
import com.compass.pb.exams.orders.utils.MappersUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    private final MappersUtils mapperUtils;

    private final ModelMapper mapper;

    public ItemDto updateItemById(long id, ItemRequest request) {
        log.debug("updateItemById() - start update item ID: {}", id);
        ItemEntity item = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ItemEntity newItem = mapperUtils.itemRequestToEntity(request);
        mapper.map(newItem, item);
        repository.save(item);

        return mapper.map(item, ItemDto.class);
    }

}
