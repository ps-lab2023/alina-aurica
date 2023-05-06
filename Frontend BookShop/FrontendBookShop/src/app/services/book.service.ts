import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Book} from "../model/Book";
import {Type} from "../model/Type";


@Injectable({
  providedIn: 'root'
})
export class BookService {

  baseURL: string = "http://localhost:8082/book";

  constructor(private httpClient: HttpClient) {
  }

  getAllBook() {
    return this.httpClient.get<Book[]>(this.baseURL + "/findAll");

  }

  getBookById(id: number) {
    let params = new HttpParams().set('id', id);
    return this.httpClient.get(this.baseURL + "/findById", {params: params})
  }

  getAllBooksByName(name: any) {
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByName" + name);
  }

  getAllBookByAuthor(author: any){
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByAuthor" + author);
  }

  getAllBookByType(type: any){
    return this.httpClient.get<Book[]>(this.baseURL + "/findAllByType" + type);
  }

  sortedBook(){
    return this.httpClient.post<Book[]>(this.baseURL + "/sortedBooks", null);
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

  deleteBookById(id: any):any{ //nu merge
    return this.httpClient.delete(this.baseURL + "/deleteById" + id,
      {headers:{'Content-Type':'application/json'},observe:'response'});
  }

  insertBook(name: any, author: any, stock: any, price: any, type: any): any{
    let credentials = {name: name, author: author, stock:stock, price: price, type: type};
    return this.httpClient.put(this.baseURL + "/insert",
      JSON.stringify(credentials) ,{headers:{'Content-Type':'application/json'},observe:'response'});
  }

  updateBookByPrice(name: any, price: any): any{
    let credentials = {name: name};
    return this.httpClient.put(this.baseURL + "/updatePrice" + price,
      JSON.stringify(credentials) ,{headers:{'Content-Type':'application/json'},observe:'response'});
  }

  updateBookByStock(name: any, stock: any): any{
    let credentials = {name: name};
    return this.httpClient.put(this.baseURL + "/updateStock" + stock,
      JSON.stringify(credentials) ,{headers:{'Content-Type':'application/json'},observe:'response'});
  }
}
