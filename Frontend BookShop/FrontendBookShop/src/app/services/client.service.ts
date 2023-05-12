import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {Client} from "../model/Client";
import {Book} from "../model/Book";
import {Bill} from "../model/Bill";
import {GiftCard} from "../model/GiftCard";

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
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {email: email};
    return this.httpClient.put('http://localhost:8082/client/addInCart' + name,
      JSON.stringify(credentials), { headers: header, observe: 'response' })
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Book[];
        })
      );
  }

  public deleteFromCart(name: any, email:any):Observable<Book[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {email: email};
    return this.httpClient.post('http://localhost:8082/client/deleteFromCart' + name,
      JSON.stringify(credentials), {headers: header, observe: 'response'})
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Book[];
        })
      );
  }

  public generateBill(email:any): Observable<Bill>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {email: email};
    return this.httpClient.post('http://localhost:8082/client/generateBill',
      JSON.stringify(credentials), {headers: header, observe: 'response'})
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as Bill;
        })
      );
  }

  public generateGiftCard(email:any, personName:any, money: any): Observable<GiftCard>{
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    let credentials = {email: email};
    return this.httpClient.post('http://localhost:8082/client/generateGiftCard' + '/' + personName + '/' + money,
      JSON.stringify(credentials), {headers: header, observe: 'response'})
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as GiftCard;
        })
      );
  }

}
