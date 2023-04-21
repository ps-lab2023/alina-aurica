package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Bill;
import com.proiectps.BookShop.model.Client;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public interface BillService {
    Optional<Bill> findById(Long id);

    Bill generateBill(Client client) throws IOException;
}
