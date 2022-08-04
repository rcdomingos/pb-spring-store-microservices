package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.builders.OrderEntityBuilder;
import com.compass.pb.exams.orders.builders.OrderRequestBuilder;
import com.compass.pb.exams.orders.domain.request.OrderRequest;
import com.compass.pb.exams.orders.dto.OrderDto;
import com.compass.pb.exams.orders.entities.OrderEntity;
import com.compass.pb.exams.orders.repositories.OrderRepository;
import com.compass.pb.exams.orders.utils.MappersUtils;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = OrderService.class)
class OrderServiceTest {

    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    @MockBean
    private RabbitService rabbitService;

    @SpyBean
    private ModelMapper mapper;

    @SpyBean
    private MappersUtils mappersUtils;

    @Test
    void shouldReturnAnOrder_whenAddANewOrderRequest() {
        OrderRequest request = OrderRequestBuilder.one().now();
        when(repository.save(any())).thenReturn(OrderEntityBuilder.one().now());

        OrderDto result = service.addNewOrder(request);

        assertNotNull(result);
        assertEquals(request.getCpf(), result.getCpf());
    }

    @Test
    void shouldSendToQueue_whenAddANewOrderRequest() {
        OrderRequest request = OrderRequestBuilder.one().now();
        when(repository.save(any())).thenReturn(OrderEntityBuilder.one().now());

        OrderDto result = service.addNewOrder(request);

        verify(repository,times(1)).save(any());
        verify(rabbitService,times(1)).sendPaymentToQueue(result);

    }

    @Test
    void shouldReturnAnOrder_whenSearchingById() {
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(OrderEntityBuilder.one().now()));

        OrderDto result = service.getOrderById(id);

        assertNotNull(result);
    }

    @Test
    void shouldDeleteOrder_whenDeleteOrderById() {
        long id = 1;
        OrderEntity entity = OrderEntityBuilder.one().now();
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteOrderById(id);

       verify(repository,times(1)).findById(id);
       verify(repository,times(1)).delete(entity);
    }

    @Test
    void shouldReturnResponseStatusException_whenSearchingAndNotFindingTheOrderById() {
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.getOrderById(id));
    }

    @Test
    void shouldUpdateAnOrder_whenTheInformatioIsCorrect() {
        long id = 1;
        String newCpf = "44455566677";
        OrderEntity order = OrderEntityBuilder.one().now();
        OrderRequest request = OrderRequestBuilder.one().withCpf(newCpf).now();
        when(repository.findById(id)).thenReturn(Optional.of(order));

        OrderDto result = service.updateOrderInformation(id, request);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(argThat((OrderEntity arg) -> arg.getCpf().equals(newCpf)));
    }

    @Test
    void shouldReturnListOrders_whenGetAllOrderns() {
        String sort = "asc";
        when(repository.findAll(any(Sort.class))).thenReturn(Arrays.asList(OrderEntityBuilder.one().now()));
        List<OrderDto> result = service.getAllOrderns(sort);

        assertNotNull(result);
        assertEquals(1,result.size());
    }


    @Test
    void shouldReturnListOrders_whenGetAllOrdernsByCpf() {
        when(repository.findAllByCpf(any(Sort.class),anyString())).thenReturn(Arrays.asList(OrderEntityBuilder.one().now()));

        List<OrderDto> result = service.getAllOrdernsByCpf("asc","12345678900");

        assertNotNull(result);
        assertEquals(1,result.size());
    }

}