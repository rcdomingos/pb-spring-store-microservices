package com.compass.pb.exams.orders.services;

import com.compass.pb.exams.orders.domain.request.OrderRequest;
import com.compass.pb.exams.orders.dto.OrderDto;
import com.compass.pb.exams.orders.entities.OrderEntity;
import com.compass.pb.exams.orders.repositories.OrderRepository;
import com.compass.pb.exams.orders.utils.MappersUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    private final ModelMapper mapper;

    private final MappersUtils mappersUtils;

    private final RabbitService rabbitService;

    public OrderDto addNewOrder(OrderRequest orderRequest) {
        log.debug("addNewOrder() - start saved ordem request:{}", orderRequest);
        OrderEntity entity = mappersUtils.orderRequestToEntity(orderRequest);
        OrderEntity saved = repository.save(entity);

        OrderDto orderDto = mapper.map(saved, OrderDto.class);

        log.debug("addNewOrder() - send order to queue");
        rabbitService.sendPaymentToQueue(orderDto);

        return orderDto;
    }

    public List<OrderDto> getAllOrderns(String sort) {
        log.debug("getAllOrderns() - start get all orders sort: {}", sort);
        Sort sorted = verifySortDirection(sort);
        List<OrderEntity> ordersList = repository.findAll(sorted);

        return ordersList.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrdernsByCpf(String sort, String cpf) {
        log.debug("getAllOrdernsByCpf() - start get all orders of cpf:{}, sort: {}", cpf, sort);
        Sort sorted = verifySortDirection(sort);
        List<OrderEntity> ordersList = repository.findAllByCpf(sorted, cpf);

        return ordersList.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        log.debug("getOrderById() - start get ordem by id:{}", id);
        OrderEntity orderEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return mapper.map(orderEntity, OrderDto.class);
    }

    public void deleteOrderById(Long id) {
        log.debug("deleteOrderById() - start delete ordem by id:{}", id);
        OrderEntity order = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(order);
    }

    public OrderDto updateOrderInformation(Long id, OrderRequest orderRequest) {
        log.debug("updateOrderInformation() - start update ordem by id:{}", id);
        OrderEntity orderEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        OrderEntity newOrder = mappersUtils.orderRequestToEntity(orderRequest);

        mapper.map(newOrder, orderEntity);

        log.debug("updateOrderInformation() - saving new object:{}", orderEntity.getCpf());
        repository.save(orderEntity);

        return mapper.map(orderEntity, OrderDto.class);
    }

    private Sort verifySortDirection(String stringSort) {
        if (stringSort == null) {
            return Sort.by("id");
        }
        Sort.Direction direction = stringSort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, "amount");
    }

}
