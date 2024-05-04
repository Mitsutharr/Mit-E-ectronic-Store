package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.entities.Order;
import com.cwm.electronic.store.entities.Product;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderItemDto {

    private int orderItemId;

    private int quantity;

    private int totalPrice;

    private Product product;

    private Order order;

}
