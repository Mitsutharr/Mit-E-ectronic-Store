package com.cwm.electronic.store.services;

import com.cwm.electronic.store.dtos.PageableResponse;
import com.cwm.electronic.store.dtos.UserDto;
import com.cwm.electronic.store.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    //create user
    UserDto createUser(UserDto userDto);
    //update user
    UserDto updateUser(UserDto userDto,String userId);
    //delete user
    void deleteUser(String userId) throws IOException;
    //get all user
    PageableResponse<UserDto> gerAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    //get single user by id
    UserDto getuserById(String userId);
    //get single user by email
    UserDto getUserByEmail(String email);
    //search user
    List<UserDto> searchUser(String keyword);

    Optional<User> findUserByEmailOptional(String email);


}
