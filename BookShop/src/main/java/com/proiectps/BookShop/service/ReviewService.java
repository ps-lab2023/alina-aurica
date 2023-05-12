package com.proiectps.BookShop.service;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewService {
    List<Review> seeReviews(Book book);

    Review writeReview(Book book, Review review);
}
