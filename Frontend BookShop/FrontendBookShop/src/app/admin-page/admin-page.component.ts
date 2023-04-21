import {Component, OnInit} from '@angular/core';
import {Book} from "../model/Book";
import {BookService} from "../services/book.service";
import {FormBuilder} from "@angular/forms";


@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  bookList:Book[] = []
  book: Book = new Book()

  constructor(private bookService:BookService,
              private formBuilder:FormBuilder
  ) {}

  ngOnInit(): void {
    this.bookService.getAllBook().subscribe(
      (result: Book[]) => {
        console.log(result);
        this.bookList = result;
      },
      (_error: Error) => {
        console.log("error");
      }
    );
  }

  insertABook(){
    console.log(this.book);
    this.bookService.insertBook(this.book.name, this.book.author, this.book.stock, this.book.price, this.book.type).subscribe(
      (bookAux: Book) => {
        console.log(bookAux);
        this.book = bookAux;
        console.log(this.book);
        alert("Insert successfully");

      },
      (_error: Error) => {
        alert("Some fields are empty. Please, write them.");
      }
    );
  }

  updatePrice(){
    console.log(this.book);
    this.bookService.updateBookByPrice(this.book.name, this.book.price).subscribe(
      (bookAux: Book) => {
        console.log(bookAux);
        this.book = bookAux;
        console.log(this.book);
        alert("Update successfully");

      },
      (_error: Error) => {
        alert("Update failed");
      }
    );
  }

  updateStock() {
    console.log(this.book);
    this.bookService.updateBookByStock(this.book.name, this.book.stock).subscribe(
      (bookAux: Book) => {
        console.log(bookAux);
        this.book = bookAux;
        console.log(this.book);
        alert("Update successfully");

      },
      (_error: Error) => {
        alert("Update failed");
      }
    );
  }

  deleteABook() { //deleteById
    console.log(this.book);
    this.bookService.deleteBookById(this.book.id).subscribe(
      (bookAux: Book) => {
        console.log(bookAux);
        this.book = bookAux;
        console.log(this.book);
        alert("Delete successfully");

      },
      (_error: Error) => {
        alert("Delete failed");
      }
    );
  }

  }
