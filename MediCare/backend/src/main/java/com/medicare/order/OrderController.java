package com.medicare.order;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final OrderRepository repository; private final OrderService service;
 public OrderController(OrderRepository repository,OrderService service){this.repository=repository;this.service=service;}
 @PostMapping public OrderEntity create(@Valid @RequestBody OrderDtos.CreateOrderRequest request){return service.create(request);}
 @GetMapping public List<OrderEntity> all(){return repository.findAll();}
}
