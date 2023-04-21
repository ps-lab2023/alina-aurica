package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface AdminService {
//    void generateBillIntoFile(Admin admin, Client client) throws IOException;
    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);

    Admin insertAdmin(Admin admin);
    Admin updateAdminByEmail(Admin admin, String email);
    Admin deleteAdmin(Admin admin);

}
