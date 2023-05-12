import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Book} from "../model/Book";
import {Type} from "../model/Type";
import {Observable} from "rxjs";
import {Review} from "../model/Review";


@Injectable({
  providedIn: 'root'
})
export class BookService {

  baseURL: string = "http://localhost:8082/book";

  constructor(private httpClient: HttpClient) {
  }

  getAllBook(): Observable<Book[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Book[]>(this.baseURL + "/findAll", {headers: header});

  }

  getBookById(id: number) {
    let params = new HttpParams().set('id', id);
    return this.httpClient.get(this.baseURL + "/findById", {params: params})
  }

  getAllBooksByName(name: any) {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByName" + name, {headers: header});
  }

  getAllBookByAuthor(author: any){
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByAuthor" + author, {headers: header});
  }

  getAllBookByType(type: any){
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByType" + type, {headers: header});
  }

  sortedBook(){
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<Book[]>(this.baseURL + "/sortedBooks", null, {headers: header});
  }


  // deleteBook():any{ //nu stiu daca merge
  //   return this.httpClient.delete(this.baseURL + "/delete",
  //     {headers:{'Content-Type':'application/json'},observe:'response'});
  // }
  //
  // deleteBookByName(name: any):any{ //nu merge
  //   return this.httpClient.delete(this.baseURL + "/deleteByName" + name,
  //     {headers:{'Content-Type':'application/json'},observe:'response'});
  // }

  deleteBookById(id: any): any {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete(this.baseURL + "/deleteById" + id,
      {headers: header});
  }

  insertBook(name: any, author: any, stock: any, price: any, type: any): any{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {name: name, author: author, stock:stock, price: price, type: type};
    return this.httpClient.put(this.baseURL + "/insert",
      JSON.stringify(credentials) ,{headers: header});
  }

  updateBookByPrice(name: any, price: any): any{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {name: name};
    return this.httpClient.put(this.baseURL + "/updatePrice" + price,
      JSON.stringify(credentials) ,{headers: header});
  }

  updateBookByStock(name: any, stock: any): any{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {name: name};
    return this.httpClient.put(this.baseURL + "/updateStock" + stock,
      JSON.stringify(credentials) ,{headers: header});
  }

  seeReviews(nameBook: any): any{
    return this.httpClient.get<Review[]>(this.baseURL + "/seeReview" + "/" + nameBook);
  }

  writeReview(nameBook: any, nameReview: any): any {
    console.log(nameBook,nameReview);
    return this.httpClient.put<Review>(this.baseURL + "/writeReview" + "/" + nameBook + "/" + nameReview, null);
  }
}
