package com.cwm.electronic.store.services.impl;

import com.cwm.electronic.store.controllers.UsserController;
import com.cwm.electronic.store.dtos.PageableResponse;
import com.cwm.electronic.store.dtos.UserDto;
import com.cwm.electronic.store.entities.Role;
import com.cwm.electronic.store.entities.User;
import com.cwm.electronic.store.exception.ResourceNotFoundExveption;
import com.cwm.electronic.store.helper.Helper;
import com.cwm.electronic.store.repositories.RoleRepository;
import com.cwm.electronic.store.repositories.UserRepository;
import com.cwm.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${user.image.profile.path}")
    private String imagePath;

    @Value("${normal.role.id}")
    private String normalRoleId;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //generate stirng id randomlyu
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //encoding password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //convert dto to entity
         User user = dtoToEntity(userDto);

        //fetch role of normal and set it to user
        Role role = roleRepository.findById(normalRoleId).get();
        user.getRoles().add(role);

        User savedUser = userRepository.save(user);
        //convert entity to dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;

    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found with  given id"));
        user.setName(userDto.getName());
        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //save data
        User updateUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updateUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId)  {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found with  given id"));

        //delete iuser profile image
        String fullPath = imagePath + user.getImageName();
        try{
            Path path = Paths.get(fullPath);
            Files.delete(path);

        }catch (NoSuchFileException ex)
        {
            logger.info("User Image not Found In Folder");
            ex.printStackTrace();
        }catch (IOException e)
        {

            e.printStackTrace();
        }

        //delete
        userRepository.delete(user);

    }

    @Override
    public PageableResponse<UserDto> gerAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        //Sort sort = Sort.by(sortBy);
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);

        return pageableResponse;
    }

    @Override
    public UserDto getuserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExveption("user not found with  given id"));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExveption("user not found wiht given email id !!    "));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public Optional<User> findUserByEmailOptional(String email) {
        return userRepository.findByEmail(email);
    }


    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .pasword(savedUser.getPasword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName()).build();
        return mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder().userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .pasword(userDto.getPasword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
        return mapper.map(userDto,User.class);

    }
}
