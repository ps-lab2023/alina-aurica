package com.proiectps.BookShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDTO {
    private Long id;
    private LocalDateTime localDateTime;
}
