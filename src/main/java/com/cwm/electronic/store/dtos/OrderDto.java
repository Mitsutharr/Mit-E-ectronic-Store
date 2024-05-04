package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.entities.OrderItem;
import com.cwm.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderDto {

    private String orderId;
    //pending , delivered , dispatch, delivered
    //enum
    private String orderStatus="PENDING";
    //not paid , paid
    //boolean
    private String paymentStatus="NOTPAID";

    private int orderAmount;
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date orderedDate=new Date();
    private Date deliveredDate;


    //private UserDto user;

    private List<OrderItemDto> orderItems = new ArrayList<>();

}
