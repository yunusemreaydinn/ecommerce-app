package com.EmreAydin.ecommerceapi.Api.Controller.Order;

import com.EmreAydin.ecommerceapi.Models.OnlineOrder;
import com.EmreAydin.ecommerceapi.Models.User;
import com.EmreAydin.ecommerceapi.Service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OnlineOrder> getOrders(@AuthenticationPrincipal User user){
        return orderService.getOrders(user);
    }


}
