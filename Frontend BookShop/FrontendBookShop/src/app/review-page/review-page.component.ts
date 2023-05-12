import {Component, OnInit} from '@angular/core';
import {Review} from "../model/Review";
import {BookService} from "../services/book.service";
import {ClientService} from "../services/client.service";
import {FormBuilder} from "@angular/forms";
import {Book} from "../model/Book";

@Component({
  selector: 'app-review-page',
  templateUrl: './review-page.component.html',
  styleUrls: ['./review-page.component.css']
})
export class ReviewPageComponent implements OnInit{
  reviewList: Review[] = []
  review: Review = new Review()
  nameBook: any
  nameReview: any

  constructor(private bookService:BookService,
              private formBuilder:FormBuilder
  ) {}


  ngOnInit(): void {
  }

  seeReviews(): void {
    console.log(this.nameBook)
    this.bookService.seeReviews(this.nameBook).subscribe(
      (result: Review[]) => {
        console.log(result);
        this.reviewList = result;
        //alert("Search successfully")
      },
      (_error: Error) => {
        alert("See review failed")
        console.log("error");
      }
    )
  }

  writeReview(): void{
    console.log(this.nameBook)
    console.log(this.nameReview)
    this.bookService.writeReview(this.nameBook, this.nameReview).subscribe(
      (result: Review) => {
        console.log(result);
        this.review = result;
        //alert("Search successfully")
      },
      (_error: Error) => {
        alert("Write review failed")
        console.log("error");
      }
    )
  }

}
