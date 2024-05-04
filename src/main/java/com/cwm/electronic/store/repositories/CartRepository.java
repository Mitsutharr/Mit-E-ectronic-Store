package com.cwm.electronic.store.repositories;

import com.cwm.electronic.store.entities.Cart;
import com.cwm.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String>{

    Optional<Cart> findByuser(User user);
}
