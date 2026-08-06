package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequest);

    Order createOrder(Order orderRequest);

    OrderResponseDto getOrderById(Long orderId);

    OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto);

    void deleteOrder(Long id);

    Map<String, Object> getDeliveryPartnerDetails(Long orderId);


}


