package com.proiectps.BookShop.service.impl;

import com.proiectps.BookShop.model.Book;
import com.proiectps.BookShop.model.Review;
import com.proiectps.BookShop.repository.BookRepository;
import com.proiectps.BookShop.repository.ReviewRepository;
import com.proiectps.BookShop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Review> seeReviews(Book book){
        return reviewRepository.findAllByBook(book);
    }

    @Override
    public Review writeReview(Book book, Review review){
//        List<Review> reviews = book.getReviews();
//        reviews.add(review);
//        book.setReviews(reviews);
//        bookRepository.save(book);

        review.setBook(book);
        reviewRepository.save(review);

        return review;
    }
}
