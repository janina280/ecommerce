package com.ecommerce.topit.dtos;

import com.ecommerce.topit.models.Product;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String specifications;

    private int stock;

    private int cost;

    private String provider;

    private String description;

    private String deliveryMethod;

    private Long categoryId;

    private double score = -1;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        specifications = product.getSpecifications();
        stock = product.getStock();
        provider = product.getProvider();
        description = product.getDescription();
        deliveryMethod = product.getDeliveryMethod();
        categoryId = product.getCategory().getId();
        cost = product.getCost();
    }

    public ProductDto(Product product, double score) {
        this(product);
        this.score = score;
    }

    public ProductDto() {
    }
}
