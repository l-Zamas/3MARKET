package com.unipacifico._3market.mapper;

import com.unipacifico._3market.dto.OrderDto;
import com.unipacifico._3market.model.Order;
import com.unipacifico._3market.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final PaymentMapper paymentMapper;

    public OrderDto toDto(Order order) {
        User user = order.getUser();
        return OrderDto.builder()
                .id(order.getId())
                .username(user.getUsername())
                .clientFullName(user.getApellido() != null ? user.getNombre() + " " + user.getApellido() : user.getNombre())
                .clientEmail(user.getEmail())
                .status(order.getStatus())
                .total(order.getTotal())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(order.getItems().stream().map(orderItemMapper::toDto).collect(Collectors.toList()))
                .payment(order.getPayment() != null ? paymentMapper.toDto(order.getPayment()) : null)
                .build();
    }

    public List<OrderDto> toDtoList(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }
}
