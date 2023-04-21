package com.proiectps.BookShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private LocalDateTime localDateTime;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    public Bill(){
        this.localDateTime = LocalDateTime.now();
    }

}
