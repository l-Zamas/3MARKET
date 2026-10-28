package com.unipacifico._3market.mapper;

import com.unipacifico._3market.dto.CartItemDto;
import com.unipacifico._3market.model.CartItem;
import com.unipacifico._3market.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartItemMapper {

    public CartItemDto toDto(CartItem item) {
        Product product = item.getProduct();
        return CartItemDto.builder()
                .id(item.getId())
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productImageUrl(product.getImageUrl())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .quantity(item.getQuantity())
                .subtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .availableStock(product.getStock())
                .build();
    }
}
