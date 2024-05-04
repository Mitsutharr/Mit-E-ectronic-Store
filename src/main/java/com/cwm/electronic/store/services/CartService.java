package com.cwm.electronic.store.services;

import com.cwm.electronic.store.dtos.AddItemToCartRequest;
import com.cwm.electronic.store.dtos.CartDto;

public interface CartService {

    //add item to cart
    //if user is adding item to cart for that if cart for the user is not available: then we creaate the cart and
    //add the item
    //case 2 : if cart available then directly add the item to cart

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item from cart

    void removeItemFromCart(String userId,int cartItem);

    //remove all item from cart
    void clearCart(String userId);

    CartDto getCartByUser(String userId);
}
