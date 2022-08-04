package com.compass.pb.exams.orders.utils;

import com.compass.pb.exams.orders.builders.ItemRequestBuilder;
import com.compass.pb.exams.orders.builders.OrderRequestBuilder;
import com.compass.pb.exams.orders.domain.request.ItemRequest;
import com.compass.pb.exams.orders.domain.request.OfferRequest;
import com.compass.pb.exams.orders.domain.request.OrderRequest;
import com.compass.pb.exams.orders.entities.ItemEntity;
import com.compass.pb.exams.orders.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MappersUtils.class)
class MappersUtilsTest {

    @Autowired
    private MappersUtils mapperUtils;

    @Test
    void shouldReturnOrdemEntity_whenMapperOrderRequest() {
        //CENARIO
        OrderRequest orderRequest = OrderRequestBuilder.one().now();

        //EXECUÇÃO
        OrderEntity result = mapperUtils.orderRequestToEntity(orderRequest);

        //VALIDAÇÃO
        assertEquals(orderRequest.getCpf(), result.getCpf());
    }

    @Test
    void shouldReturnItemEntity_whenMapperItemRequest() {
        ItemRequest request = ItemRequestBuilder.one().withValue(99.9).withName("Item").now();

        ItemEntity result = mapperUtils.itemRequestToEntity(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getValue(), result.getSaleValue());
    }


    @Test
    void shouldReturnOfferEntity_whenOfferDateIsCorrect() {
        OfferRequest request = new OfferRequest();
        request.setExpirationDate(LocalDateTime.now().plusDays(30));

        assertDoesNotThrow(() -> mapperUtils.offerRequestToEntity(request));
    }

}