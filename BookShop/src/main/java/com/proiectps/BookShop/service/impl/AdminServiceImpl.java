package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.service.AdminService;
import com.proiectps.BookShop.validator.AdminValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    AdminValidator adminValidator = new AdminValidator();

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

//    @Override
//    public void generateBillIntoFile(Admin admin, Client client) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter("Bill"));
//        writer.write(admin.generateBill(client));
//
//        writer.close();
//    }

    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        return adminRepository.findAdminByEmailAndPassword(email, password);
    }

    @Override
    public Admin findByEmail(String email) {
        //System.out.println(adminRepository.findAdminByEmail(email));
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        try {
            adminValidator.validateAdmin(admin);
            if(adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword()) == null) {
                //adminRepository.save(admin);
                return adminRepository.save(admin);
            }
            else return null;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Admin updateAdminByEmail(Admin admin, String email) {
        try {
            adminValidator.validateAdmin(admin);
            adminValidator.validateEmail(email);
            Admin updateAdmin = adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword());
            adminValidator.validateAdmin(updateAdmin);
            updateAdmin.setEmail(email);
            adminRepository.save(updateAdmin);
            /*
            if(updateAdmin != null){
                updateAdmin.setEmail(email);
                adminRepository.save(updateAdmin);
            }
            */
            return updateAdmin;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Admin deleteAdmin(Admin admin) {
        try {
            adminValidator.validateAdmin(admin);
            Admin deleteAdmin = adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword());
            adminValidator.validateAdmin(deleteAdmin);
            adminRepository.delete(deleteAdmin);
            /*
            if(deleteAdmin != null)
                adminRepository.delete(deleteAdmin);
             */
            return deleteAdmin;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }
}
