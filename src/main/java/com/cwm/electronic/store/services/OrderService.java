package com.cwm.electronic.store.services;

import com.cwm.electronic.store.dtos.CreateOrderRequest;
import com.cwm.electronic.store.dtos.OrderDto;
import com.cwm.electronic.store.dtos.PageableResponse;
import com.cwm.electronic.store.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    //create order

    OrderDto createOrder(CreateOrderRequest orderDto);

    void removeOrder(String orderId);



    //remove order

    //get orders of user

    List<OrderDto> getOrderOfUsers(String userId);

    //get orders

    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);

    //other methods related to order
}
