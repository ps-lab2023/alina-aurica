package com.proiectps.BookShop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiftCardDTO {
    private Long idGiftCard;
    private String personName;
    private Float money;
    private Long numberCard;
    private LocalDateTime localDateTime;
    private String time;
}
