package com.ecommerce.topit.models;

import com.ecommerce.topit.security.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
