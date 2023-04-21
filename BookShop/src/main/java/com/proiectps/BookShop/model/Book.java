package com.proiectps.BookShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book { //cred ca pot sa scap de client de aici
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;

    private String author;
    private Integer stock;
    private Float price;
    private Type type;


    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Client client;

    /*
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;
    */

    public void deleteClient(){
        client = null;
    }

    @Override
    public String toString() {
        if(client == null){
            return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", type=" + type ;}
        else
            return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", type=" + type +
                ", client=" + client +
                '}';
    }
}
