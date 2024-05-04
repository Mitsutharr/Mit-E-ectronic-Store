package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.entities.Cart;
import com.cwm.electronic.store.entities.CartItem;
import com.cwm.electronic.store.entities.Product;
import com.cwm.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private int cartItemId;


    private ProductDto product;

    private  int quantity;

    private int totalPrice;


}
