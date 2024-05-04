package com.cwm.electronic.store.repositories;

import com.cwm.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    //iski implementation run time p spring ki or se apne ap ajaegi

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);

    List<User> findByNameContaining(String keywords);

}
