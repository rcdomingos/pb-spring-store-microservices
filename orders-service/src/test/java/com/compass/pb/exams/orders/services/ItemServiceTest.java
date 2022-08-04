package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.builders.ItemEntityBuilder;
import com.compass.pb.exams.orders.builders.ItemRequestBuilder;
import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.dto.ItemDto;
import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.repositories.ItemRepository;
import com.compass.pb.exams.orders.utils.MappersUtils;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ItemService.class)
class ItemServiceTest {

    @Autowired
    private ItemService service;

    @MockBean
    private ItemRepository repository;

    @SpyBean
    private MappersUtils mapperUtils;

    @SpyBean
    private ModelMapper mapper;

    @Test
    void shouldUpdateAnItem_whenTheInformatioIsCorrect() {
        long id = 1;
        Double newValue = 123.4;
        ItemEntity item = ItemEntityBuilder.one().now();
        ItemRequest request = ItemRequestBuilder.one().withValue(newValue).now();
        when(repository.findById(id)).thenReturn(Optional.of(item));

        ItemDto result = service.updateItemById(id, request);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(argThat((ItemEntity arg) -> arg.getSaleValue().equals(newValue)));
    }

}