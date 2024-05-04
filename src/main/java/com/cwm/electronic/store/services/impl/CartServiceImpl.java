package com.cwm.electronic.store.services.impl;

import com.cwm.electronic.store.dtos.AddItemToCartRequest;
import com.cwm.electronic.store.dtos.CartDto;
import com.cwm.electronic.store.entities.Cart;
import com.cwm.electronic.store.entities.CartItem;
import com.cwm.electronic.store.entities.Product;
import com.cwm.electronic.store.entities.User;
import com.cwm.electronic.store.exception.BadApiRequest;
import com.cwm.electronic.store.exception.ResourceNotFoundExveption;
import com.cwm.electronic.store.repositories.CartItemRepository;
import com.cwm.electronic.store.repositories.CartRepository;
import com.cwm.electronic.store.repositories.ProductRepository;
import com.cwm.electronic.store.repositories.UserRepository;
import com.cwm.electronic.store.services.CartService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity<=0)
        {
            throw new BadApiRequest("Requested quantity is not valid ");
        }
        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundExveption("product not found in database"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found in database"));
        //
        Cart cart = null;
        try {
            cart=cartRepository.findByuser(user).get();
        }catch (NoSuchElementException e)
        {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //perform cart operation

        //if cart item alreafdy present then update
        //boolean updated = false;
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountedPrice());
                updated.set(true);

            }


            return item;
        }).collect(Collectors.toList());

        //cart.setItems(updatedItems);

        // create items
       if(!updated.get())
       {
           CartItem cartItem = CartItem.builder()
                   .quantity(quantity)
                   .totalPrice(quantity * product.getDiscountedPrice())
                   .cart(cart)
                   .product(product)
                   .build();

           cart.getItems().add(cartItem);
       }
        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);

        return mapper.map(updatedCart,CartDto.class);


    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {

        //conditon


        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundExveption("Cart Item not found "));
        cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found in database"));
        Cart cart = cartRepository.findByuser(user).orElseThrow(() -> new ResourceNotFoundExveption(" cart of given user not found !!"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found in database"));
        Cart cart = cartRepository.findByuser(user).orElseThrow(() -> new ResourceNotFoundExveption(" cart of given user not found !!"));

        return mapper.map(cart,CartDto.class);
    }
}
