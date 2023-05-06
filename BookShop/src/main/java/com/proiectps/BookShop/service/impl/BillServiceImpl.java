package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Bill;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.repository.BillRepository;
import com.proiectps.BookShop.repository.ClientRepository;
import com.proiectps.BookShop.service.BillService;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.utils.BillUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookService bookService;

    public BillServiceImpl(BillRepository billRepository, ClientRepository clientRepository){
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill generateBill(Client client) throws IOException {
        Bill bill = new Bill();
        bill.setClient(client);
        Bill bill1 = billRepository.save(bill);
        List<Bill> bills = client.getBills();
        bills.add(bill);
        client.setBills(bills);
        clientRepository.save(client);
        BillUtils.generateBillIntoFile(client, bookService);
        return bill1;
    }



}
