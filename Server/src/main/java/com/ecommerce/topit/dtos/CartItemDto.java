package com.ecommerce.topit.dtos;

import com.ecommerce.topit.models.CartItem;
import lombok.Data;

@Data
public class CartItemDto {

    private Long id;

    private int quantity;

    private Long productId;

    public CartItemDto(CartItem cartItem) {
        id = cartItem.getId();
        quantity = cartItem.getQuantity();
        productId = cartItem.getProduct().getId();
    }

    public CartItemDto() {

    }
}
