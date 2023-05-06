import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {BehaviorSubject, map, Observable} from 'rxjs';
import {User} from "../model/user";
import {Book} from "../model/Book";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL:string="http://localhost:8082/user";
  ownerDataStream:any;

  constructor(private httpClient:HttpClient) {
    this.ownerDataStream=new BehaviorSubject<any>(null);
  }


  public login(email: any, password: any): Observable<User> {
    let credentials = { email: email, password: password };
    return this.httpClient.post('http://localhost:8082/user/login',
      JSON.stringify(credentials), { headers: { 'Content-Type': 'application/json' }, observe: 'response' })
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as User;
        })
      );
  }

  public logout(email: any): Observable<User> {
    let credentials = { email: email};
    return this.httpClient.post('http://localhost:8082/user/logout',
      JSON.stringify(credentials), { headers: { 'Content-Type': 'application/json' }, observe: 'response' })
      .pipe(
        map((response: HttpResponse<any>) => {
          return response.body as User;
        })
      );
  }

  public register(firstName: any, lastName: any, email: any, password: any, role: any): any
  {
    let credentials = {firstName: firstName, lastName: lastName, email: email, password: password, role:role}
    return this.httpClient.put('http://localhost:8082/user/register',
      JSON.stringify(credentials), {headers:{'Content-Type':'application/json'}, observe:'response'});
  }

  public viewProfile(email: any): any
  {
    return this.httpClient.get(this.baseURL + "/findByEmail" + email)
  }

  public changePassword(email: any, password: any): any
  {
    let credentials = {email: email}
    return this.httpClient.put('http://localhost:8082/user/updatePassword' + '/' + password,
      JSON.stringify(credentials), {headers:{'Content-Type':'application/json'}, observe:'response'})
  }

  getAllUsers() {
    return this.httpClient.get<User[]>(this.baseURL + "/findAll");
  }
}
