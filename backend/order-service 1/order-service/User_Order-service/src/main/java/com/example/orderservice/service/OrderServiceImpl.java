package com.example.orderservice.service;

import com.example.orderservice.dto.DeliveryDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.entity.Delivery;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.DeliveryRepository;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        // 1️⃣ Create Order
        Order order = new Order();
        order.setUserId(generateUserId());
        order.setOrderType(orderRequestDto.getOrderType());
        order.setAddress(orderRequestDto.getAddress());
        order.setSubscriptionId(orderRequestDto.getSubscriptionId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setStatus(orderRequestDto.getStatus());
        order.setPaymentId(orderRequestDto.getPaymentId());
        order.setNotes(orderRequestDto.getNotes());
        order.setAppliedDiscount(orderRequestDto.getAppliedDiscount());

        // 2️⃣ Map and link OrderItems
        List<OrderItem> orderItems = orderRequestDto.getItems().stream().map(itemDto -> {
            OrderItem item = new OrderItem();
            item.setMealName(itemDto.getMealName());
            item.setQuantity(itemDto.getQuantity());
            item.setPricePerItem(itemDto.getPricePerItem());
            item.setCustomizations(itemDto.getCustomizations());

            // 🔥 Link item to parent order
            item.setOrder(order);

            return item;
        }).toList();

        order.setItems(orderItems);

        // 3️⃣ Save Order (cascades items)
        Order savedOrder = orderRepository.save(order);

        // 4️⃣ Handle Delivery
        Delivery savedDelivery = null;
        if (orderRequestDto.getDelivery() != null) {
            Delivery delivery = new Delivery();
            delivery.setOrder(savedOrder);
            delivery.setPartnerName(orderRequestDto.getDelivery().getDeliveryPartnerName());
            delivery.setPartnerPhone(orderRequestDto.getDelivery().getDeliveryPartnerPhone());
            savedDelivery = deliveryRepository.save(delivery);
        }

        return mapOrderToDto(savedOrder, savedDelivery);
    }


    @Override
    public Order createOrder(Order orderRequest) {
        if (orderRequest.getItems() != null) {
            List<OrderItem> items = orderRequest.getItems();
            for (OrderItem item : items) {
                item.setOrder(orderRequest);
            }
        }

        return orderRepository.save(orderRequest);
    }


    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Delivery delivery = deliveryRepository.findByOrderId(orderId);
        return mapOrderToDto(order, delivery);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long id, OrderRequestDto orderRequestDto) {

        // 1️⃣ Fetch existing order
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setOrderType(orderRequestDto.getOrderType());
        order.setAddress(orderRequestDto.getAddress());
        order.setSubscriptionId(orderRequestDto.getSubscriptionId());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setStatus(orderRequestDto.getStatus());
        order.setPaymentId(orderRequestDto.getPaymentId());
        order.setNotes(orderRequestDto.getNotes());
        order.setAppliedDiscount(orderRequestDto.getAppliedDiscount());

        // 2️⃣ Handle OrderItems
        // Map new items from DTO
        List<OrderItem> updatedItems = orderRequestDto.getItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setMealName(dto.getMealName());
            item.setQuantity(dto.getQuantity());
            item.setPricePerItem(dto.getPricePerItem());
            item.setCustomizations(dto.getCustomizations());

            // 🔥 Link to parent order
            item.setOrder(order);

            return item;
        }).toList();

        // Clear old items and add new ones
        order.getItems().clear();
        order.getItems().addAll(updatedItems);

        // 3️⃣ Save updated order (cascade saves items)
        orderRepository.save(order);

        // 4️⃣ Handle Delivery
        Delivery delivery = deliveryRepository.findByOrderId(id);
        if (orderRequestDto.getDelivery() != null) {
            if (delivery == null) {
                delivery = new Delivery();
                delivery.setOrder(order);
            }
            delivery.setPartnerName(orderRequestDto.getDelivery().getDeliveryPartnerName());
            delivery.setPartnerPhone(orderRequestDto.getDelivery().getDeliveryPartnerPhone());
            deliveryRepository.save(delivery);
        }

        return mapOrderToDto(order, delivery);
    }


    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepository.delete(order);
    }


    private String generateUserId() {
        char first = (char) ('A' + new Random().nextInt(26));
        char second = (char) ('A' + new Random().nextInt(26));
        int number = new Random().nextInt(10000);
        return String.format("%c%c%04d", first, second, number);
    }

    private OrderResponseDto mapOrderToDto(Order order, Delivery delivery) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(order.getId());
        responseDto.setUserId(order.getUserId());
        responseDto.setAddress(order.getAddress());
        responseDto.setOrderType(order.getOrderType());
        responseDto.setSubscriptionId(order.getSubscriptionId());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setTotalAmount(order.getTotalAmount());
        responseDto.setStatus(order.getStatus());
        responseDto.setPaymentId(order.getPaymentId());
        responseDto.setNotes(order.getNotes());
        responseDto.setAppliedDiscount(order.getAppliedDiscount());

        List<OrderItemDto> itemsDto = order.getItems().stream().map(item -> {
            OrderItemDto dto = new OrderItemDto();
            dto.setMealName(item.getMealName());
            dto.setQuantity(item.getQuantity());
            dto.setPricePerItem(item.getPricePerItem());
            dto.setCustomizations(item.getCustomizations());
            return dto;
        }).toList();
        responseDto.setItems(itemsDto);

        if (delivery != null) {
            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setDeliveryId(delivery.getId());
            deliveryDto.setDeliveryPartnerName(delivery.getPartnerName());
            deliveryDto.setDeliveryPartnerPhone(delivery.getPartnerPhone());
            responseDto.setDelivery(deliveryDto);
        }

        return responseDto;
    }

    @Override
    public Map<String, Object> getDeliveryPartnerDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Delivery delivery = deliveryRepository.findByOrderId(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());

        if (delivery != null) {
            response.put("deliveryPartnerName", delivery.getPartnerName());
            response.put("deliveryPartnerPhone", delivery.getPartnerPhone());
        } else {
            response.put("deliveryPartnerName", "Not Assigned");
            response.put("deliveryPartnerPhone", "N/A");
        }
        return response;
    }

}
