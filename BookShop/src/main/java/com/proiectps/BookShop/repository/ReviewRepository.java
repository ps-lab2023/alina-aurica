package com.proiectps.BookShop.repository;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByBook(Book book);
}
