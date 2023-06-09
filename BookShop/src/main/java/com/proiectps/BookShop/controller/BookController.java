package com.proiectps.BookShop.controller;

import com.proiectps.BookShop.DTO.BookDTO;
import com.proiectps.BookShop.DTO.ReviewDTO;
import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Review;
import com.proiectps.BookShop.model.Type;
import com.proiectps.BookShop.service.BookService;
import com.proiectps.BookShop.service.ReviewService;
import com.proiectps.BookShop.validator.BookValidator;
import com.proiectps.BookShop.validator.exception.WrongAndNullException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ModelMapper modelMapper;

    private final BookValidator bookValidator = new BookValidator();

    @GetMapping("/findById{id}")
    public ResponseEntity getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
    }

    @GetMapping("/findAll")
    public List<BookDTO> getAllBook() {
        List<Book> books = bookService.findAll();
        List<BookDTO> booksDTO = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return booksDTO;
    }

    @GetMapping("/findAllByName{name}")
    public ResponseEntity getBooksByName(@PathVariable String name) {
        List<Book> books = bookService.searchFromName(name);
        //System.out.println("Suntem aici");
        List<BookDTO> booksDTO = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @GetMapping("/findAllByAuthor{author}")
    public ResponseEntity getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.searchFromAuthor(author);
        //System.out.println("Suntem aici");
        List<BookDTO> booksDTO = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @GetMapping("/findAllByType{type}")
    public ResponseEntity getBooksByType(@PathVariable Type type) {
        List<Book> books = bookService.searchFromType(type);
        //System.out.println("Suntem aici");
        List<BookDTO> booksDTO = books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(booksDTO);
    }

    @DeleteMapping("/delete")
    public BookDTO deleteBook(@RequestBody Book book) {
        Book book1 = bookService.deleteBook(book);
        BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);

        return bookDTO;
    }

    @Transactional
    @DeleteMapping("/deleteByName{name}") //nu merge -- iei
    public BookDTO deteleBookByName(@PathVariable String name) {
        Book book1 = bookService.deleteBookByName(name);
        // BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);

        //return bookDTO;
        return null;

    }

    @DeleteMapping("/deleteById{id}")
    public BookDTO deteleBookById(@PathVariable Long id) {
        Optional<Book> book1 = bookService.deleteById(id);
        BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);

        return bookDTO;

    }

    @PutMapping("/updatePrice{price}")
    public BookDTO updateBookByPrice(@RequestBody Book book, @PathVariable Float price) {
        Book book1 = bookService.updateBookByPrice(book, price);
        BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);
        return bookDTO;
    }

    @PutMapping("/updateStock{stock}")
    public BookDTO updateBookByStock(@RequestBody Book book, @PathVariable int stock) {
        Book book1 = bookService.updateBookByStock(book, stock);
        BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);
        return bookDTO;
    }

    @PutMapping("/insert")
    public BookDTO insertBook(@RequestBody Book book) {

        try {
            Book book1 = bookService.insertBook(book);
            bookValidator.validateBook(book1);
            BookDTO bookDTO = modelMapper.map(book1, BookDTO.class);
            return bookDTO;
        } catch (WrongAndNullException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/sortedBooks")
    public List<BookDTO> sortedBooks() {
        List<Book> sortedBooks = bookService.sortBooks();
        List<BookDTO> booksDTO = sortedBooks.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
        return booksDTO;
    }

    @GetMapping("/seeReview/{nameBook}")
    public ResponseEntity seeReviews(@PathVariable String nameBook) {
        Book book = bookService.findByName(nameBook);
        List<Review> reviews = reviewService.seeReviews(book);
        List<ReviewDTO> reviewsDTO = reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(reviewsDTO);
    }

    @PutMapping("/writeReview/{nameBook}/{nameReview}")
    public ResponseEntity writeReview(@PathVariable String nameBook, @PathVariable String nameReview) {
        log.info(nameBook,nameReview);
        Book book = bookService.findByName(nameBook);
        Review review = new Review();
        review.setReview(nameReview);
        Review review1 = reviewService.writeReview(book, review);
        ReviewDTO reviewDTO = modelMapper.map(review1, ReviewDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(reviewDTO);
    }

}
