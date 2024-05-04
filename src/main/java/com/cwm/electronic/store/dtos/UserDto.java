package com.cwm.electronic.store.dtos;

import com.cwm.electronic.store.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private String userId;

    @Size(min = 3,max = 25,message = "invalid name !!")
    private String name;

    //@Email(message = "invalid User email !!")
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9](([a-zA-Z0-9,=\\.!\\-#|\\$%\\^&\\*\\+/\\?_`\\{\\}~]+)*)@(?:[0-9a-zA-Z-]+\\.)+[a-zA-Z]{2,9}",message = "invalid user email")
    private String email;

    @NotBlank(message = "password is required !!")
    private String password;

    @Size(min =4 , max = 6,message = "invalid gendre !!")
    private String gender;

    @NotBlank(message = "Write Something about Yourself !! ")
    private String about;

    @ImageNameValid
    private String imageName;

    private Set<RoleDto> roles = new HashSet<>();
    //custom validator

}
