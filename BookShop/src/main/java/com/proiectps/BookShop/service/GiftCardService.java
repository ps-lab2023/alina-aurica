package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.GiftCard;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface GiftCardService {
    GiftCard generateGiftCard(Client client, String personName, Float money) throws IOException;
}
