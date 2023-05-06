package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Bill;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.GiftCard;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.repository.GiftCardRepository;
import com.proiectps.BookShop.service.GiftCardService;
import com.proiectps.BookShop.utils.BillUtils;
import com.proiectps.BookShop.utils.GiftCardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GiftCardServiceImpl implements GiftCardService {
    @Autowired
    private GiftCardRepository giftCardRepository;

    @Autowired
    private ClientRepository clientRepository;

    public GiftCardServiceImpl(GiftCardRepository giftCardRepository, ClientRepository clientRepository){
        this.giftCardRepository = giftCardRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public GiftCard generateGiftCard(Client client, String personName, Float money) throws IOException {
        GiftCard giftCard = new GiftCard();
        giftCard.setPersonName(personName);
        giftCard.setMoney(money);
        giftCard.setClient(client);
        GiftCard giftCard1 = giftCardRepository.save(giftCard);
        List<GiftCard> giftCards = client.getGiftCards();
        giftCards.add(giftCard1);
        client.setGiftCards(giftCards);
        clientRepository.save(client);
        GiftCardUtils.generateGiftCardIntoFile(giftCard);
        return giftCard1;
    }
}
