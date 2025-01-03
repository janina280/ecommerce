package com.ecommerce.topit.controllers;

import com.ecommerce.topit.dtos.OrderDto;
import com.ecommerce.topit.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200/")
@PreAuthorize("hasRole('USER')")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public OrderDto createOrder() {
        return orderService.createOrder();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<OrderDto> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @DeleteMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public Map<String, String> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return Collections.singletonMap("response", "Order " + id + " has been canceled.");
    }
}
