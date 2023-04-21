package com.proiectps.BookShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {
    private Long idAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
