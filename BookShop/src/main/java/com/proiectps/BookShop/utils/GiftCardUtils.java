package com.proiectps.BookShop.utils;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.GiftCard;
import com.proiectps.BookShop.service.BookService;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiftCardUtils {
    public static String generateGiftCard(GiftCard giftCard){
        StringBuilder str = new StringBuilder();
        str.append("TO: " + giftCard.getPersonName() + "\n" +
                   "Money " + giftCard.getMoney() + "\n" +
                   "No of card " + giftCard.getNumberCard() + "\n" +
                   "Current date: " + giftCard.getLocalDateTime() + "\n" +
                   giftCard.getTime());
        return str.toString();
    }

    public static void generateGiftCardIntoFile(GiftCard giftCard) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("GiftCard" + ".pdf"));
        writer.write(generateGiftCard(giftCard));

        writer.close();
    }
}
