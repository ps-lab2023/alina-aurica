package com.proiectps.BookShop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

import static java.lang.Math.random;

@Entity
@AllArgsConstructor
@Builder
@Data
public class GiftCard {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long idGiftCard;
    private String personName;
    private Float money;
    private Long numberCard;
    private LocalDateTime localDateTime;
    private String time; //aici ar trebui sa fie o constanta

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    public GiftCard(){
        Random rand = new Random();
        localDateTime = LocalDateTime.now();
        time = "Availability: 5 days";
        numberCard = rand.nextLong();
    }

}
