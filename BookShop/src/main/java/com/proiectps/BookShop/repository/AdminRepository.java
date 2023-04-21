package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findAdminByEmailAndPassword(String email, String password);
    Admin findAdminByEmail(String email);

}
