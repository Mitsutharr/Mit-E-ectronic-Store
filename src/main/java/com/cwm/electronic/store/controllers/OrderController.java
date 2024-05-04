package com.cwm.electronic.store.controllers;

import com.cwm.electronic.store.dtos.ApiResponseMessage;
import com.cwm.electronic.store.dtos.CreateOrderRequest;
import com.cwm.electronic.store.dtos.OrderDto;
import com.cwm.electronic.store.dtos.PageableResponse;
import com.cwm.electronic.store.repositories.OrderRepository;
import com.cwm.electronic.store.services.OrderService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request)
    {
        OrderDto order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public  ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId)
    {
        orderService.removeOrder(orderId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .status(HttpStatus.OK)
                .message("order is removed !!")
                .success(true)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);


    }

    //get order of user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId)
    {
        List<OrderDto> orderOfUsers = orderService.getOrderOfUsers(userId);

        return new ResponseEntity<>(orderOfUsers,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10" ,required = false) int pageSize,
            @RequestParam(value = "soryBy",defaultValue = "orderedDate" ,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc" ,required = false) String sortDir
    )
    {
        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
