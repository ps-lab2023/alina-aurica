package com.proiectps.BookShop.controller;

import com.proiectps.BookShop.DTO.AdminDTO;
import com.proiectps.BookShop.DTO.ClientDTO;
import com.proiectps.BookShop.model.Admin;
import com.proiectps.BookShop.model.Client;
import com.proiectps.BookShop.service.AdminService;
import com.proiectps.BookShop.service.ClientService;
import com.proiectps.BookShop.validator.AdminValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    private final AdminValidator adminValidator = new AdminValidator();

    @GetMapping("/findByEmail")
    public ResponseEntity findAdminByEmail(@RequestParam String email){
        Admin admin = adminService.findByEmail(email);
        AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity findAdminByEmailAndPassword(@RequestParam String email, String password){
        Admin admin = adminService.findByEmailAndPassword(email, password);
        AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
    }


    @DeleteMapping("/delete")
    public AdminDTO deleteAdmin(@RequestBody Admin admin){
        Admin admin1 = adminService.deleteAdmin(admin);
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);

        return adminDTO;
    }

    @PutMapping("/updateEmail")
    public AdminDTO updateAdminByEmail(@RequestBody Admin admin, @RequestParam String email){

        Admin admin1 = adminService.updateAdminByEmail(admin, email);
        AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);
        return adminDTO;
    }

    @PutMapping("/insert")
    public AdminDTO insertAdmin(@RequestBody Admin admin){

        try {
            Admin admin1 = adminService.insertAdmin(admin);
            adminValidator.validateAdmin(admin1);
            AdminDTO adminDTO = modelMapper.map(admin1, AdminDTO.class);
            return adminDTO;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }

    }
}
