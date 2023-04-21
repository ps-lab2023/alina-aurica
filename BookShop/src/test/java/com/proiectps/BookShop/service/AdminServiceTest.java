package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.model.Role;
import com.proiectps.BookShop.repository.AdminRepository;
import com.proiectps.BookShop.service.impl.AdminServiceImpl;
import com.proiectps.BookShop.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminServiceTest { //aici mai am da facut insert, update, delete in AdminService
    private static final String EMAIL = "ion.marin@bookshop.com";
    private static final String EMAIL2 = "ion.marin10@bookshop.com";
    private static final String EMAIL_NOT = "mihai.pop@gmail";


    private static final String PASSWORD = "admin";
    private static final String PASSWORD_NOT = "ana";

    private AdminServiceImpl adminServiceImpl;

    @Mock
    private AdminRepository adminRepository;

    private Admin admin;
    private Admin admin2;
    private Admin admin3;
    private Admin admin4;

    @BeforeEach
    void init(){
        initMocks(this);
        admin = new Admin();
        admin.setIdAdmin(1L);
        admin.setEmail(EMAIL);
        admin.setPassword(PASSWORD);

        admin2 = new Admin();
        admin2.setIdAdmin(2L);
        admin2.setFirstName("Maria");
        admin2.setLastName("Ionescu");
        admin2.setEmail("maria.ionescu@gmail.com");
        admin2.setPassword("maaria");

        Admin adminAux = new Admin();
        adminAux.setIdAdmin(2L);
        adminAux.setFirstName("Maria");
        adminAux.setLastName("Ionescu");
        adminAux.setEmail("maria.ionescu@gmail.com");
        adminAux.setPassword("maaria");

        admin3 = new Admin();
        admin3.setIdAdmin(1L);
        admin3.setEmail(EMAIL2);
        admin3.setPassword(PASSWORD);

        admin4 = new Admin();
        admin4.setIdAdmin(1L);
        admin4.setEmail(EMAIL2);
        admin4.setPassword(PASSWORD);
        //client3.setPassword("maaria");

        when(adminRepository.findAdminByEmail(EMAIL)).thenReturn(admin);
        when(adminRepository.findAdminByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(admin);
        when(adminRepository.save(adminAux)).thenReturn(admin2);
        when(adminRepository.save(admin3)).thenReturn(admin4);
        doNothing().when(adminRepository).delete(admin);
        when(adminRepository.findById(1L)).thenReturn(Optional.ofNullable(admin4));
    }


    @Test
    void givenExistingEmail_whenFindByEmail_thenFindOne(){
        adminServiceImpl = new AdminServiceImpl(adminRepository);
        Admin adminAux = new Admin();
        adminAux.setEmail(EMAIL);
        //System.out.println(adminAux);
        Admin admin = adminServiceImpl.findByEmail(EMAIL);
        //System.out.println(adminServiceImpl.findByEmail(EMAIL));

        assertNotNull(admin);
        assertEquals(EMAIL, admin.getEmail());
    }

    @Test
    void givenNotExistingEmail_whenFindByEmail_thenThrowExcepsion(){
        when(adminRepository.findAdminByEmail(EMAIL_NOT)).thenReturn(null);

        Admin adminAux = new Admin();
        adminAux.setEmail(EMAIL_NOT);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            adminServiceImpl.findByEmail(EMAIL_NOT);
        });
    }

//    @Test
//    void givenExistingEmailAndPassword_whenFindByEmailAndPassword_thenFindOne(){
//        adminServiceImpl = new AdminServiceImpl(adminRepository);
//        Admin adminAux = new Admin();
//        adminAux.setEmail(EMAIL);
//        adminAux.setPassword(PASSWORD);
//        //System.out.println(adminAux);
//        Admin admin = adminServiceImpl.findByEmailAndPassword(adminAux);
//        //System.out.println(userServiceImpl.findByEmail(userAux));
//
//        assertNotNull(admin);
//        assertEquals(EMAIL, admin.getEmail());
//        assertEquals(PASSWORD, admin.getPassword());
//    }

//    @Test
//    void givenNotExistingEmailAndPassword_whenFindByEmailAndPassword_thenThrowExcepsion(){
//        when(adminRepository.findAdminByEmailAndPassword(EMAIL, PASSWORD_NOT)).thenReturn(null);
//
//        Admin adminAux = new Admin();
//        adminAux.setEmail(EMAIL);
//        adminAux.setPassword(PASSWORD_NOT);
//
//        Exception exception = assertThrows(NullPointerException.class, () -> {
//            adminServiceImpl.findByEmailAndPassword(adminAux);
//        });
//    }


    @Test
    void givenExistingAdmin_whenInsertAdmin_thenFindOne(){
        adminServiceImpl = new AdminServiceImpl(adminRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Admin adminAux = new Admin();
        adminAux.setIdAdmin(2L);
        adminAux.setFirstName("Maria");
        adminAux.setLastName("Ionescu");
        adminAux.setEmail("maria.ionescu@gmail.com");
        adminAux.setPassword("maaria");

        Admin admin = adminServiceImpl.insertAdmin(adminAux);

        assertNotNull(admin);
        assertEquals("maria.ionescu@gmail.com", admin.getEmail());
    }

    @Test
    void givenNonExistingBook_whenInsertBook_thenFindFirstOne(){
        Admin adminAux = new Admin();
        adminAux.setIdAdmin(1L);
        adminAux.setEmail(EMAIL);
        adminAux.setPassword(PASSWORD);
        when(adminRepository.save(adminAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            adminServiceImpl.insertAdmin(adminAux);
        });
    }

    @Test
    void givenExistingAdmin_whenUpdateAdminByEmail_thenFindOne(){
        adminServiceImpl = new AdminServiceImpl(adminRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Admin adminAux = new Admin();
        adminAux.setIdAdmin(1L);
        adminAux.setEmail(EMAIL);
        Admin admin = adminServiceImpl.updateAdminByEmail(adminAux, EMAIL2);

        assertNotNull(admin);
        assertEquals(EMAIL2, admin.getEmail());
    }

    @Test
    void givenNonExistingAdmin_whenUpdateAdminByEmail_thenFindFirstOne(){
        Admin adminAux = new Admin();
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        adminAux.setEmail(EMAIL_NOT);
        //bookAux.setName(NAME_NOT);
        when(adminRepository.save(adminAux)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            adminServiceImpl.updateAdminByEmail(adminAux, EMAIL2);
        });
    }


    @Test
    void doNothingAdmin_whenDelete(){
        adminServiceImpl = new AdminServiceImpl(adminRepository);
        //System.out.println(bookRepository.findByName(NAME));
        //System.out.println(bookRepository.findAllByName(NAME).toString());

        //bookServiceImpl.searchFromName(NAME);

        Admin adminAux = new Admin();
        adminAux.setIdAdmin(1L);
        adminAux.setEmail(EMAIL);
        adminAux.setPassword(PASSWORD);

        Admin admin = adminServiceImpl.deleteAdmin(adminAux);

        assertNotNull(admin);
        //assertEquals(NAME, book.getName());
    }


    @Test
    void notDoNothingAdmin_whenDelete(){
        Admin adminAux = new Admin();
        adminAux.setIdAdmin(6L);
        //Book bookAux2 = new Book();
        //bookAux.setId(bookRepository.findByName(NAME).getId());
        adminAux.setEmail(EMAIL_NOT);
        //bookAux.setName(NAME_NOT);
        adminAux.setPassword(PASSWORD);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            adminServiceImpl.deleteAdmin(adminAux);
        });
    }

}
