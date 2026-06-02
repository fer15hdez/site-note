package cursoSpringBoot.controller;

import cursoSpringBoot.domain.Order;
import cursoSpringBoot.domain.OrderRepository;
import cursoSpringBoot.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/db")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
}
