import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {Client} from "../model/Client";
import {Book} from "../model/Book";
import {Bill} from "../model/Bill";

@Injectable({
  providedIn: 'root'
})
export class ClientService{
  baseURL:string="http://localhost:8082/client";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream=new BehaviorSubject<any>(null);
  }

  public addInCart(name: any, email: any): Observable<Book[]> {
    let credentials = {email: email};
    return this.httpClient.put('http://localhost:8082/client/addInCart' + name,
      JSON.stringify(credentials), { headers: { 'Content-Type': 'application/json' }, observe: 'response' })
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Book[];
        })
      );
  }

  public deleteFromCart(name: any, email:any):Observable<Book[]> {
    let credentials = {email: email};
    return this.httpClient.post('http://localhost:8082/client/deleteFromCart' + name,
      JSON.stringify(credentials), {headers: {'Content-Type': 'application/json'}, observe: 'response'})
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Book[];
        })
      );
  }

  public generateBill(email:any): Observable<Bill>{
    let credentials = {email: email};
    return this.httpClient.post('http://localhost:8082/client/generateBill',
      JSON.stringify(credentials), {headers: {'Content-Type': 'application/json'}, observe: 'response'})
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Bill;
        })
      );
  }
}
