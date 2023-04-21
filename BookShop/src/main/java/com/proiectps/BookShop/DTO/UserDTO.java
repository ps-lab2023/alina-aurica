package com.proiectps.BookShop.DTO;

import com.proiectps.BookShop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    //@NotBlank
    private String firstName;
    //@NotBlank
    private String lastName;
    //@NotBlank
    private String email;
    //@NotBlank
    private String password;
    //@NotBlank
    private Role role;
}
