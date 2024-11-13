package com.ecommerce.topit.repos;

import com.ecommerce.topit.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
