package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.entities.Order;
import com.cwm.electronic.store.entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    @NotBlank(message = "cart id is required !!")
    private String cartId;
    @NotBlank(message = "user id is required !!")
    private String userId;

    //pending , delivered , dispatch, delivered
    //enum

    private String orderStatus="PENDING";
    //not paid , paid
    //boolean
    private String paymentStatus="NOTPAID";

    @NotBlank(message = "Address id is required !!")
    private String billingAddress;
    @NotBlank(message = "Phone number id is required !!")
    private String billingPhone;
    @NotBlank(message = "Billing name id is required !!")
    private String billingName;




    //private UserDto user;




}
