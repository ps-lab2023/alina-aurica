import {Component, OnInit} from '@angular/core';
import {Book} from "../model/Book";
import {BookService} from "../services/book.service";
import {ClientService} from "../services/client.service";
import {FormBuilder} from "@angular/forms";
import {Bill} from "../model/Bill";
import jwt_decode from "jwt-decode";


@Component({
  selector: 'app-client-page',
  templateUrl: './client-page.component.html',
  styleUrls: ['./client-page.component.css']
})
export class ClientPageComponent implements OnInit{

  bookList: Book[] = []
  book: Book = new Book()
  cartList: Book[] = []
  bill: Bill = new Bill()

  constructor(private bookService:BookService,
              private clientService:ClientService,
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

  searchByName(): void{
    console.log(this.book)
    this.bookService.getAllBooksByName(this.book.name).subscribe(
      (result: Book[]) => {
        console.log(result);
        this.bookList = result;
        //alert("Search successfully")
      },
      (_error: Error) => {
        alert("Search failed")
        console.log("error");
      }
    )
  }

  searchByAuthor(): void{
    console.log(this.book)
    this.bookService.getAllBookByAuthor(this.book.author).subscribe(
      (result: Book[]) => {
        console.log(result);
        this.bookList = result;
        //alert("Search successfully")
      },
      (_error: Error) => {
        alert("Search failed")
        console.log("error");
      }
    )
  }

  searchByType(): void{
    console.log(this.book)
    this.bookService.getAllBookByType(this.book.type).subscribe(
      (result: Book[]) => {
        console.log(result);
        this.bookList = result;
        //alert("Search successfully")
      },
      (_error: Error) => {
        alert("Search failed")
        console.log("error");
      }
    )
  }

  sortedBooks(): void{
    this.bookService.sortedBook().subscribe(
      (result: Book[]) => {
        console.log(result);
        this.bookList = result;
      },
      (_error: Error) => {
        alert("Sorted failed");
      }
    );
  }

  addInCart(){
    console.log(this.book)
    const user: any = localStorage.getItem("token")
    console.log(user)
    var tokenPayload: any;
    tokenPayload = jwt_decode(user)
    this.clientService.addInCart(this.book.name, tokenPayload.sub).subscribe(
      (cart: Book[]) => {
        console.log(cart)
        this.cartList = cart
        alert("Book add successfully")

    },
      (_error: Error) => {
        alert("Book add failed")
      })
  }

  deleteFromCart(){
    console.log(this.book)
    const user: any = localStorage.getItem("token")
    var tokenPayload: any;
    tokenPayload = jwt_decode(user)
    this.clientService.deleteFromCart(this.book.name, tokenPayload.sub).subscribe(
      (cart: Book[]) => {
        console.log(cart)
        this.cartList = cart
        alert("Book delete successfully")

      },
      (_error: Error) => {
        alert("Book delete failed")
      })
  }

  generateBill(){
    console.log(this.book)
    const user: any = localStorage.getItem("token")
    var tokenPayload: any;
    tokenPayload = jwt_decode(user)
    this.clientService.generateBill(tokenPayload.sub).subscribe(
      (billAux : Bill) =>{
        console.log(billAux)
        this.bill = billAux
        alert("Generate bill successfully")
      },
      (_error: Error) => {
        alert("Generate failed")
      })
  }

}
