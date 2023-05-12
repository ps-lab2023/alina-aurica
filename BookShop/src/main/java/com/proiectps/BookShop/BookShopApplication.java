package com.proiectps.BookShop;

import com.proiectps.BookShop.model.*;
import com.proiectps.BookShop.repository.*;
import com.proiectps.BookShop.service.AdminService;
import com.proiectps.BookShop.service.ClientService;
import com.proiectps.BookShop.service.UserService;
import com.proiectps.BookShop.service.impl.BookServiceImpl;
import com.proiectps.BookShop.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BookShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}


	@Bean
	CommandLineRunner init(UserRepository userRepo, BookRepository bookRepo, ClientRepository clientRepo, AdminRepository adminRepo, BillRepository billRepo, UserServiceImpl userService, BookServiceImpl bookService, ClientService clientService, AdminService adminService){
		return args -> {
//			Client client = new Client();
//			Bill bill = new Bill();
//			Admin admin = new Admin();
//
			//Partea de register si logIn
			User user = new User(1L, "Mihai", "Pop", "mihai.pop@gmail.com", "asdfg", Role.CLIENT, false);
			User user2 = new User(2L, "Ion", "Marin", "ion.marin@bookshop.com", "admin", Role.ADMIN, false);
			userService.register(user);
			userService.registerAdmin(user2);
			//userService.register(user2); //admin
//			//userService.logIn(user.getEmail(), user.getPassword());
//			System.out.println(userService.findByEmail(user.getEmail()));
//			if(userService.logIn(user.getEmail(), user.getPassword()).getRole() == Role.CLIENT) {
//				client = clientService.findByEmail(userService.logIn(user.getEmail(), user.getPassword()).getEmail());
//				clientRepo.save(client);
//			}
//			else{
//				admin = adminService.findByEmail(userService.logIn(user.getEmail(), user.getPassword()).getEmail());
//				adminRepo.save(admin);
//			}

			//crearea de carti si inserarea in baza de date
//            Book book1 = new Book();
//			book1.setName("De veghe in lanul de secara");
//			book1.setAuthor("J. D. Saligner");
//			book1.setStock(76);
//			book1.setPrice(35.0f);
//			book1.setType(Type.BESTSELLER);

			Book book2 = new Book();
			book2.setName("Franny si Zoey");
			book2.setAuthor("J. D. Saligner");
			book2.setStock(24);
			book2.setPrice(24.6f);
			book2.setType(Type.BESTSELLER);

			Book book3 = new Book();
			book3.setName("Mandrie si prejudecata");
			book3.setAuthor("Jane Austen");
			book3.setStock(38);
			book3.setPrice(47.5f);
			book3.setType(Type.ROMANCE);

            List<Book> books = new ArrayList<Book>();
//			books.add(book1);
			books.add(book2);
			books.add(book3);

			bookRepo.saveAll(books);
//
//			//clientul introduce o carte in cosul de cuparaturi
//			client.addInCart(book1);
//			clientRepo.save(client);
//			book1.setClient(client);
//			bookRepo.save(book1);
//			//bookService.searchFromAuthor("J. D. Saligner");
//			bill.setClient(client);
//			billRepo.save(bill);
//			client.setBill(bill);
//			clientRepo.save(client);

//			client.addInCart(book3);
//			clientRepo.save(client);
//			book3.setClient(client);
//			bookRepo.save(book3);
//
//			//clientul soliticta calcularea subtotalului (pretul pe cartile pe care intentioneaza sa le cumpere)
//			System.out.println("Subtotal " + Float.toString(client.calculatePrice()));
//
//			//clientul sterge o carte din cosul de cuparaturi
//			client.deleteFromCart(book3);
//			clientRepo.save(client);
//			book3.deleteClient();
//			bookRepo.save(book3);
//
//			//OPERATII PE CRUD
//			//clientul face search dupa anumite criterii
//			System.out.println(bookService.searchFromAuthor("J. D. Saligner").toString());
//			System.out.println(bookService.searchFromName("De veghe in lanul de secara").toString());
//			System.out.println(bookService.searchFromName("Mandrie si prejudecata").toString());
//			System.out.println(bookService.searchFromType(Type.ROMANCE).toString());

			//insert & delete & update pe DB a cartilor -- functionalitati de admin
			Book book4 = new Book();
			book4.setName("Cei 3 muschetari");
			book4.setAuthor("Alexandre Dumas");
			book4.setStock(30);
			book4.setPrice(40.7f);
			book4.setType(Type.BESTSELLER);

			Book book5 = new Book();
			book5.setName("Emma");
			book5.setAuthor("Jane Austen");
			book5.setStock(20);
			book5.setPrice(35.5f);
			book5.setType(Type.BESTSELLER);


            bookService.insertBook(book4);
			bookService.insertBook(book5);
            bookService.updateBookByPrice(book4, 25.6f); //face update la price
            bookService.updateBookByStock(book4, 30); //face update la price
			bookService.deleteBook(book2);
			//bookService.deleteBook(book5);
//
//			//functionalitati admin -- crearea unei facturi
//			userService.logIn(user2.getEmail(), user2.getPassword());
//			if(userService.logIn(user2.getEmail(), user2.getPassword()).getRole() == Role.CLIENT) {
//				client = clientService.findByEmail(userService.logIn(user2.getEmail(), user2.getPassword()).getEmail());
//				clientRepo.save(client);
//			}
//			else{
//				admin = adminService.findByEmail(userService.logIn(user2.getEmail(), user2.getPassword()).getEmail());
//				adminRepo.save(admin);
//			}
//
//			System.out.println(admin.generateBill(client));
//			adminService.generateBillIntoFile(admin, client);
//
//			//INSERT, DELETE, UPDATE PE ADMIN, CLIENT SI USER :)) -- doar teste
//			Client clientNew = new Client();
//			clientNew.setFirstName("Irina");
//			clientNew.setLastName("Margarit");
//			clientNew.setEmail("irina.margarit@yahoo.ro");
//			clientNew.setPassword("irina10");
//			clientService.insertClient(clientNew);
//			Client c = clientService.updateClientByEmail(clientNew, "irinaMargarit@gmail.com");
//			clientService.deleteClient(c); //da, make sense, numa ca nu imi dau seama cum sa repar -- o sa aflam
//
//			Admin adminNew = new Admin();
//			adminNew.setFirstName("Irina");
//			adminNew.setLastName("Margarit");
//			adminNew.setEmail("irina.margarit@yahoo.ro");
//			adminNew.setPassword("irina10");
//			adminService.insertAdmin(adminNew);
//			Admin a = adminService.updateAdminByEmail(adminNew, "irinaMargarit@gmail.com");
//			adminService.deleteAdmin(a);

			//userService.changeEmail(user, "alabala");
			//userService.deleteUser(user);
//			Client client = new Client();
//			client.setFirstName("ANa");
//			client.setLastName("ajad");
//			client.setPassword("ejbhve");
//			client.setEmail("ana@yahoo.com");
//			clientService.insertClient(client);
//			clientService.addInCart("Emma", client);
//			//clientService.addInCart("Mandrie si prejudecata", client);
//			System.out.println(client.getBooks());
//			System.out.println(clientRepo.findById(client.getIdClient()).get().getBooks());
		};
	}
}
