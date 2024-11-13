package com.ecommerce.topit.repos;

import com.ecommerce.topit.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId (Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);
}
