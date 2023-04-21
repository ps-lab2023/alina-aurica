package com.proiectps.BookShop.DTO;

import com.proiectps.BookShop.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private Long id;
    private String name;
    private String author;
    private Integer stock;
    private Float price;
    private Type type;
}
