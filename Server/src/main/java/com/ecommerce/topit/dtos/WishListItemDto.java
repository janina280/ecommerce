package com.ecommerce.topit.dtos;

import com.ecommerce.topit.models.CartItem;
import com.ecommerce.topit.models.WishListItem;
import lombok.Data;

@Data
public class WishListItemDto {

    private Long id;

    private Long productId;

    private Integer userId;
    
    public WishListItemDto(WishListItem wishListItem) {
        id = wishListItem.getId();
        productId = wishListItem.getProduct().getId();
        userId = wishListItem.getUser().getId();
    }

    public WishListItemDto() {

    }
}
