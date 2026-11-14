package com.unipacifico._3market.mapper;

import com.unipacifico._3market.dto.CartDto;
import com.unipacifico._3market.dto.CartItemDto;
import com.unipacifico._3market.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartDto toDto(Cart cart) {
        List<CartItemDto> items = cart.getItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
        return CartDto.builder()
                .id(cart.getId())
                .items(items)
                .total(items.stream().map(CartItemDto::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add))
                .itemCount(items.stream().mapToInt(CartItemDto::getQuantity).sum())
                .build();
    }
}
